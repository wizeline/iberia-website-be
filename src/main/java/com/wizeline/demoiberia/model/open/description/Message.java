package com.wizeline.demoiberia.model.open.description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("Message")
public class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = -8058543104254814528L;

    private final String role;
    private final String content;

    @JsonCreator
    public Message(final String role, final String content) {
        this.role = role;
        this.content = content;
    }

    @JsonProperty("role")
    public String getRole() {
        return this.role;
    }

    @JsonProperty("content")
    public String getContent() {
        return this.content;
    }
}
