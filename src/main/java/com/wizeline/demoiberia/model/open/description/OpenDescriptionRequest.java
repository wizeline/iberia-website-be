package com.wizeline.demoiberia.model.open.description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonTypeName("DescriptionRequest")
public class OpenDescriptionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3326261700571540140L;


    private final String model;
    private final List<Message> messages;

    @JsonCreator
    public OpenDescriptionRequest(final String model, final List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    @JsonProperty("model")
    public String getModel() {
        return this.model;
    }

    @JsonProperty("messages")
    public List<Message> getMessages() {
        return this.messages;
    }
}
