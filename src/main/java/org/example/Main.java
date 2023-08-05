package org.example;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {



       Properties props = new Properties();
       props.put(StreamsConfig.APPLICATION_ID_CONFIG , AppConfigs.applicationID);
       props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
       props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,0);
       props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);

       StreamsBuilder streamsBuilder = new StreamsBuilder();

       LeaderBoardTopology.withBuilder(streamsBuilder);

       KafkaStreams streams = new KafkaStreams(streamsBuilder.build(),props);

       logger.info("Starting Stream...");
       streams.start();

       Runtime.getRuntime().addShutdownHook(new Thread( () -> {
           logger.info("Shutting down streams....");
           streams.close();
       }));




    }
}