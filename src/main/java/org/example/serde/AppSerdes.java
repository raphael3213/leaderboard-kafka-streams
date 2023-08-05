/*
 * Copyright (c) 2019. Prashant Kumar Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package org.example.serde;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.types.*;
//import org.example.types.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for Serdes
 *
 * @author prashant
 * @author www.learningjournal.guru
 */

public class AppSerdes extends Serdes {


    static final class ProductSerde extends Serdes.WrapperSerde<Product> {
        ProductSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Product> Product() {
        ProductSerde serde = new ProductSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Product.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class PlayerSerde extends Serdes.WrapperSerde<Player> {
        PlayerSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Player> Player() {
        PlayerSerde serde = new PlayerSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Player.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class ScoreEventSerde extends Serdes.WrapperSerde<ScoreEvent> {
        ScoreEventSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<ScoreEvent> ScoreEvent() {
        ScoreEventSerde serde = new ScoreEventSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, ScoreEvent.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class GroupingKeySerde extends Serdes.WrapperSerde<GroupingKey> {
        GroupingKeySerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<GroupingKey> GroupingKey() {
        GroupingKeySerde serde = new GroupingKeySerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, GroupingKey.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class ResultSerde extends Serdes.WrapperSerde<Result> {
        ResultSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Result> Result() {
        ResultSerde serde = new ResultSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Result.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class Top3PlayersSerde extends Serdes.WrapperSerde<Top3Players> {
        Top3PlayersSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Top3Players> Top3Players() {
        Top3PlayersSerde serde = new Top3PlayersSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Top3Players.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
