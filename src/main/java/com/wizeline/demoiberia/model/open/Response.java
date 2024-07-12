package com.wizeline.demoiberia.model.open;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonTypeName("Request")
public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = -5677499015496414230L;

    private final long created;
    private final List<Data> data;

    @JsonCreator
    public Response(final long created, final List<Data> data) {
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
