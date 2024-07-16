package com.wizeline.demoiberia.model.open.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonTypeName("ImageResponse")
public class ImageResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -5677499015496414230L;

    private final long created;
    private final List<Data> data;

    @JsonCreator
    public ImageResponse(final long created, final List<Data> data) {
        this.created = created;
        this.data = data;
    }

    @JsonProperty("created")
    public long getCreated() {
        return this.created;
    }

    @JsonProperty("data")
    public List<Data> getData() {
        return this.data;
    }
}
