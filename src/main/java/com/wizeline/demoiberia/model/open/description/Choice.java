package com.wizeline.demoiberia.model.open.description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("choice")
public class Choice implements Serializable {

    @Serial
    private static final long serialVersionUID = -5134482345982170590L;

    private final long index;
    private final String logprobs;
    private final String finishReason;
    private final Message message;

    @JsonCreator
    public Choice(final long index, final String logprobs, final String finishReason, final Message message) {
        this.index = index;
        this.logprobs = logprobs;
        this.finishReason = finishReason;
        this.message = message;
    }

    @JsonProperty("index")
    public long getIndex() {
        return this.index;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return this.message;
    }

    @JsonProperty("logprobs")
    public String getLogprobs() {
        return this.logprobs;
    }

    @JsonProperty("finish_reason")
    public String getFinishReason() {
        return this.finishReason;
    }
}
