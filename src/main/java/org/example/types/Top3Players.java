package org.example.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "top3Players"
})
public class Top3Players {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final  TreeSet<Result> treeSet = new TreeSet<>(
            Comparator.comparing(Result::getTotalScore)
    );


    public void add(Result result){
        treeSet.add(result);
        if(treeSet.size() > 3){
            treeSet.remove(treeSet.last());
        }
    }


    public void remove(Result result) {
        treeSet.remove(result);
    }

    @JsonProperty("Top3Players")
    public String get() throws JsonProcessingException {
        return objectMapper.writeValueAsString(treeSet);
    }

    @JsonProperty("Top3Players")
    public String set(String top3Players) throws IOException {
        Result[] resultList= objectMapper.readValue(top3Players, Result[].class);
        for(Result i : resultList){
            this.add(i);
        }
        return objectMapper.writeValueAsString(treeSet);
    }




}
