package com.wizeline.demoiberia.model.open.description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("usage")
public class Usage implements Serializable {

    @Serial
    private static final long serialVersionUID = 5149173482192605802L;


    private final long promptTokens;
    private final long completionTokens;
    private final long totalTokens;

    @JsonCreator
    public Usage(final long promptTokens, final long completionTokens, final long totalTokens) {
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
    }

    @JsonProperty("prompt_tokens")
    public long getPromptTokens() {
        return this.promptTokens;
    }

    @JsonProperty("completion_tokens")
    public long getCompletionTokens() {
        return this.completionTokens;
    }

    @JsonProperty("total_tokens")
    public long getTotalTokens() {
        return this.totalTokens;
    }
}
