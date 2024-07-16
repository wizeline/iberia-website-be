package com.wizeline.demoiberia.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@JsonTypeName("DescriptionRequest")
public class DescriptionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3254970173775039583L;

    private String prompt;


    @JsonCreator
    public DescriptionRequest(final String prompt) {
        this.prompt = prompt;

    }

    @JsonProperty("prompt")
    public String getPrompt() {
        return this.prompt;
    }
    

}
