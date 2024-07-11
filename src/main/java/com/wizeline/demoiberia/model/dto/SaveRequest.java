package com.wizeline.demoiberia.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("SaveRequest")
@Setter
public class SaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2176998117618122173L;


    private String url;
    private final String tag;
    private final String destination;

    @JsonCreator
    public SaveRequest(final String url, final String tag, final String destination) {
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
