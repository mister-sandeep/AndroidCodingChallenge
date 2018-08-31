package com.deepers.ripetomatoes.data;

/**
 * Created by Deepers on 8/25/2018.
 * This is the data model for a Multimedia element found within a {@link com.deepers.ripetomatoes.data.MovieReview} element.
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
public class Multimedia implements Serializable {
    private static final long serialVersionUID = -7561093572324641097L;

    @JsonProperty("type")
    private String type;
    @JsonProperty("src")
    private String src;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;

    public Multimedia() {

    }

    public Multimedia(String type, String src, int width, int height) {
        super();
        this.type = type;
        this.src = src;
        this.width = width;
        this.height = height;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("src")
    public String getSrc() {
        return src;
    }

    @JsonProperty("src")
    public void setSrc(String src) {
        this.src = src;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int width) {
        this.width = width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                "type='" + type + '\'' +
                ", src='" + src + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Multimedia that = (Multimedia) o;

        return new EqualsBuilder()
                .append(width, that.width)
                .append(height, that.height)
                .append(type, that.type)
                .append(src, that.src)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(src)
                .append(width)
                .append(height)
                .toHashCode();
    }
}
