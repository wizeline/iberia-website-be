package com.wizeline.demoiberia.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("VideoResponse")
@Setter
public class VideoResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -6305532076013957928L;

    private final String url;
    private final String tag;
    private final String destination;

    @JsonCreator
    public VideoResponse(final String url, final String tag, final String destination) {
        this.url = url;
        this.tag = tag;
        this.destination = destination;
    }

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }


    @JsonProperty("tag")
    public String getTag() {
        return this.tag;
    }

    @JsonProperty("destination")
    public String getDestination() {
        return this.destination;
    }

}
