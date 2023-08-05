
package org.example.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "score",
    "player_id",
    "product_id"
})
public class ScoreEvent {

    @JsonProperty("score")
    private Double score;
    @JsonProperty("player_id")
    private Long playerId;
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
    }

    public ScoreEvent withScore(Double score) {
        this.score = score;
        return this;
    }

    @JsonProperty("player_id")
    public Long getPlayerId() {
        return playerId;
    }

    @JsonProperty("player_id")
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public ScoreEvent withPlayerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    @JsonProperty("product_id")
    public Long getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ScoreEvent withProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("score", score).append("playerId", playerId).append("productId", productId).toString();
    }

}
