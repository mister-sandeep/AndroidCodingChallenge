package com.deepers.ripetomatoes.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Created by Deepers on 8/25/2018.
 * This is the data model for a single Movie Review element.
 * The annotations you see here are for Jackson to marshall to and from json.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "display_title",
        "mpaa_rating",
        "critics_pick",
        "byline",
        "headline",
        "summary_short",
        "publication_date",
        "opening_date",
        "date_updated",
        "link",
        "multimedia"
})
public class MovieReview implements Serializable {
    private static final long serialVersionUID = 1510659521384841319L;

    @JsonProperty("display_title")
    private String displayTitle;
    @JsonProperty("mpaa_rating")
    private String mpaaRating;
    @JsonProperty("critics_pick")
    private Boolean criticsPick;
    @JsonProperty("byline")
    private String byline;
    @JsonProperty("headline")
    private String headline;
    @JsonProperty("summary_short")
    private String summaryShort;
    @JsonProperty("publication_date")
    private String publicationDate;
    @JsonProperty("opening_date")
    private String openingDate;
    @JsonProperty("date_updated")
    private String dateUpdated;
    @JsonProperty("link")
    private Link link;
    @JsonProperty("multimedia")
    private Multimedia multimedia;


    /**
     * No args constructor for use in serialization
     *
     */
    public MovieReview() {
    }

    public MovieReview(String displayTitle, String mpaaRating, Boolean criticsPick, String byline, String headline, String summaryShort, String publicationDate, String openingDate, String dateUpdated, Link link, Multimedia multimedia) {
        this.displayTitle = displayTitle;
        this.mpaaRating = mpaaRating;
        this.criticsPick = criticsPick;
        this.byline = byline;
        this.headline = headline;
        this.summaryShort = summaryShort;
        this.publicationDate = publicationDate;
        this.openingDate = openingDate;
        this.dateUpdated = dateUpdated;
        this.link = link;
        this.multimedia = multimedia;
    }

    @JsonProperty("display_title")
    public String getDisplayTitle() {
        return displayTitle;
    }

    @JsonProperty("display_title")
    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    @JsonProperty("mpaa_rating")
    public String getMpaaRating() {
        return mpaaRating;
    }

    @JsonProperty("mpaa_rating")
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    @JsonProperty("critics_pick")
    public Boolean getCriticsPick() {
        return criticsPick;
    }

    @JsonProperty("critics_pick")
    public void setCriticsPick(Boolean criticsPick) {
        this.criticsPick = criticsPick;
    }

    @JsonProperty("byline")
    public String getByline() {
        return byline;
    }

    @JsonProperty("byline")
    public void setByline(String byline) {
        this.byline = byline;
    }

    @JsonProperty("headline")
    public String getHeadline() {
        return headline;
    }

    @JsonProperty("headline")
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @JsonProperty("summary_short")
    public String getSummaryShort() {
        return summaryShort;
    }

    @JsonProperty("summary_short")
    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    @JsonProperty("publication_date")
    public String getPublicationDate() {
        return publicationDate;
    }

    @JsonProperty("publication_date")
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @JsonProperty("opening_date")
    public String getOpeningDate() {
        return openingDate;
    }

    @JsonProperty("opening_date")
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    @JsonProperty("date_updated")
    public String getDateUpdated() {
        return dateUpdated;
    }

    @JsonProperty("date_updated")
    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @JsonProperty("link")
    public Link getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(Link link) {
        this.link = link;
    }

    @JsonProperty("multimedia")
    public Multimedia getMultimedia() {
        return multimedia;
    }

    @JsonProperty("multimedia")
    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "displayTitle='" + displayTitle + '\'' +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", criticsPick=" + criticsPick +
                ", byline='" + byline + '\'' +
                ", headline='" + headline + '\'' +
                ", summaryShort='" + summaryShort + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", openingDate='" + openingDate + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", link=" + link +
                ", multimedia=" + multimedia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MovieReview that = (MovieReview) o;

        return new EqualsBuilder()
                .append(displayTitle, that.displayTitle)
                .append(mpaaRating, that.mpaaRating)
                .append(criticsPick, that.criticsPick)
                .append(byline, that.byline)
                .append(headline, that.headline)
                .append(summaryShort, that.summaryShort)
                .append(publicationDate, that.publicationDate)
                .append(openingDate, that.openingDate)
                .append(dateUpdated, that.dateUpdated)
                .append(link, that.link)
                .append(multimedia, that.multimedia)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(displayTitle)
                .append(mpaaRating)
                .append(criticsPick)
                .append(byline)
                .append(headline)
                .append(summaryShort)
                .append(publicationDate)
                .append(openingDate)
                .append(dateUpdated)
                .append(link)
                .append(multimedia)
                .toHashCode();
    }
}