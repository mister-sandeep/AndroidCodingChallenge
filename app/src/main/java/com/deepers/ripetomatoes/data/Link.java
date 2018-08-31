package com.deepers.ripetomatoes.data;

/**
 * Created by Deepers on 8/25/2018.
 * This is the data model for a Link element found within a {@link com.deepers.ripetomatoes.data.MovieReview} element.
 * The annotations you see here are for Jackson to marshall to and from json.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "type",
        "url",
        "suggested_link_text"
})
public class Link implements Serializable {
    private static final long serialVersionUID = -8228695049015749501L;

    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("suggested_link_text")
    private String suggestedLinkText;

    public Link() {

    }

    public Link(String type, String url, String suggestedLinkText) {
        super();
        this.type = type;
        this. url = url;
        this.suggestedLinkText = suggestedLinkText;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("suggested_link_text")
    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    @JsonProperty("suggested_link_text")
    public void setSuggestedLinkText(String suggestedLinkText) {
        this.suggestedLinkText = suggestedLinkText;
    }

    @Override
    public String toString() {
        return "Link{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", suggestedLinkText='" + suggestedLinkText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return new EqualsBuilder()
                .append(type, link.type)
                .append(url, link.url)
                .append(suggestedLinkText, link.suggestedLinkText)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(url)
                .append(suggestedLinkText)
                .toHashCode();
    }
}
