package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.serde.AppSerdes;
import org.example.types.*;

public class LeaderBoardTopology {

    private static final Logger logger = LogManager.getLogger();
    static void withBuilder(StreamsBuilder streamsBuilder) {

        logger.info("In topology");
        KStream<Long, ScoreEvent> KS0 = streamsBuilder.stream(AppConfigs.scoreEventTopicName,
                Consumed.with(AppSerdes.String(), AppSerdes.ScoreEvent())).map((k,v)->{
                    return KeyValue.pair(v.getPlayerId(), v);
        });


        KS0.peek((k,v)->{
            logger.info("KS0 ==> KEY : " + k + " VALUE : " + v);
        });
//        KTable<Long, Player> KT1 = streamsBuilder.table(AppConfigs.playerTopicName,
//                Materialized.with(AppSerdes.Long(), AppSerdes.Player()));
//
//        GlobalKTable<String, Product> GKT2 = streamsBuilder.globalTable(AppConfigs.productTopicName,
//                Consumed.with(AppSerdes.String(), AppSerdes.Product()));

        KGroupedStream<GroupingKey, ScoreEvent> KG3 = KS0.groupBy((player_id , scoreEvent)->{
            return new GroupingKey().withPlayerId(scoreEvent.getPlayerId()).withProductId(scoreEvent.getProductId());
        }, Grouped.with(AppSerdes.GroupingKey(), AppSerdes.ScoreEvent()));

        KTable<GroupingKey, Double> KT4 = KG3.aggregate(()->{
                return 0D;
            }, (groupingKey, scoreEvent, aLong) -> {
                return (aLong > scoreEvent.getScore())? aLong : scoreEvent.getScore();
            },
            Materialized.<GroupingKey, Double, KeyValueStore<Bytes , byte[]>>as("score-event-kstore-hyper-new")
                    .withKeySerde(AppSerdes.GroupingKey())
                    .withValueSerde(AppSerdes.Double())
        );

        KT4.toStream().peek((k,v)->{
            System.out.println("KEY : " + k + " VALUE : " + v);
        });


        KGroupedTable<Long, Result> KGT5 = KT4.groupBy(((groupingKey, aDouble) -> {
            return KeyValue.pair(groupingKey.getProductId(), new Result().withPlayerId(groupingKey.getPlayerId()).withProductId(groupingKey.getProductId()).withTotalScore(aDouble));
        }), Grouped.with(AppSerdes.Long(),AppSerdes.Result()));

        KTable<Long , Top3Players> KT6 = KGT5.aggregate(
                Top3Players::new,
                ((aLong, result, top3Players) -> {

                    top3Players.add(result);
                    return top3Players;
                }),
                (aLong, result, top3Players) -> {
                    top3Players.remove(result);
                    return top3Players;
                },
                Materialized.<Long, Top3Players , KeyValueStore<Bytes , byte[]>>as("top3-result-store")
                        .withKeySerde(AppSerdes.Long())
                        .withValueSerde(AppSerdes.Top3Players())

        );

        KT6.toStream().peek((k,v)->{
            try {
                System.out.println("KT6 => KEY : " + k + " VALUE : " + v.get());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
