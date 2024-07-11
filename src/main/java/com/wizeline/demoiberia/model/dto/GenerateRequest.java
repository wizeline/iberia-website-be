package com.wizeline.demoiberia.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@JsonTypeName("GenerateRequest")
public class GenerateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2176998117618122173L;

    private String prompt;
    // Use open or free
    private String engine;

    @JsonCreator
    public GenerateRequest(final String prompt,  String engine) {
        this.prompt = prompt;
        this.engine = engine;

    }

    @JsonProperty("prompt")
    public String getPrompt() {
        return this.prompt;
    }

    @JsonProperty("engine")
    public String getEngine() {
        return this.engine;
    }

}
