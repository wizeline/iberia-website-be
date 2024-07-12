package com.wizeline.demoiberia.model.open;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("Data")
public class Data implements Serializable {

    @Serial
    private static final long serialVersionUID = -4387346814214265525L;

    private final String revisedPrompt;
    private final String url;

    @JsonCreator
    public Data(final String revisedPrompt, final String url) {
        this.revisedPrompt = revisedPrompt;
        this.url = url;
    }

    @JsonProperty("revised_prompt")
    public String getRevisedPrompt() {
        return this.revisedPrompt;
    }

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }
}
