
package com.github.dsaouda.listadesejos.dto.b2w;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("reviews")
    @Expose
    private Integer reviews;
    @SerializedName("recommendations")
    @Expose
    private Integer recommendations;
    @SerializedName("average")
    @Expose
    private Double average;

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public Integer getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Integer recommendations) {
        this.recommendations = recommendations;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

}
