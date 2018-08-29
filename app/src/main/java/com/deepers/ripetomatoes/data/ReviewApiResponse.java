package com.deepers.ripetomatoes.data;

/**
 * Created by Deepers on 8/25/2018.
 * This is the data model for the NYTimes movie reviews API response.
 * The annotations you see here are for Jackson to marshall to and from json.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "status",
        "copyright",
        "has_more",
        "num_results",
        "results"
})
public class ReviewApiResponse {
    @JsonProperty("status")
    public String status;
    @JsonProperty("copyright")
    public String copyright;
    @JsonProperty("has_more")
    public boolean hasMore;
    @JsonProperty("num_results")
    private int numResults;
    @JsonProperty("results")
    private List<MovieReview> results;

    public ReviewApiResponse() {

    }

    public ReviewApiResponse(String status, String copyright, boolean hasMore, int numResults, List<MovieReview> results) {
        this.status = status;
        this.copyright = copyright;
        this.hasMore = hasMore;
        this.numResults = numResults;
        this.results = results;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("has_more")
    public boolean isHasMore() {
        return hasMore;
    }

    @JsonProperty("has_more")
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @JsonProperty("num_results")
    public int getNumResults() {
        return numResults;
    }

    @JsonProperty("num_results")
    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    @JsonProperty("results")
    public List<MovieReview> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<MovieReview> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ReviewApiResponse{" +
                "status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", hasMore=" + hasMore +
                ", numResults=" + numResults +
                ", results=" + results +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ReviewApiResponse results1 = (ReviewApiResponse) o;

        return new EqualsBuilder()
                .append(hasMore, results1.hasMore)
                .append(numResults, results1.numResults)
                .append(status, results1.status)
                .append(copyright, results1.copyright)
                .append(results, results1.results)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(status)
                .append(copyright)
                .append(hasMore)
                .append(numResults)
                .append(results)
                .toHashCode();
    }
}
