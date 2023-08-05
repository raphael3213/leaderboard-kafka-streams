
package org.example.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "player_id",
    "product_id",
    "total_score"
})
public class Result {

    @JsonProperty("player_id")
    private Long playerId;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("total_score")
    private Double totalScore;

    @JsonProperty("player_id")
    public Long getPlayerId() {
        return playerId;
    }

    @JsonProperty("player_id")
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Result withPlayerId(Long playerId) {
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

    public Result withProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    @JsonProperty("total_score")
    public Double getTotalScore() {
        return totalScore;
    }

    @JsonProperty("total_score")
    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Result withTotalScore(Double totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("playerId", playerId).append("productId", productId).append("totalScore", totalScore).toString();
    }

}
