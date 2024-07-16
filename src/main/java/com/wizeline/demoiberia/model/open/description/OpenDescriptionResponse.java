package com.wizeline.demoiberia.model.open.description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonTypeName("DescriptionResponse")
public class OpenDescriptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 2232605063973231454L;

    private final String id;
    private final String object;
    private final int created;
    private final String model;
    private final String systemFingerPrint;
    private final Usage usage;
    private final List<Choice> choices;

    @JsonCreator
    public OpenDescriptionResponse(final String id, final String model, final int created, final String object, final String systemFingerPrint, final Usage usage, final List<Choice> choices) {
        this.id = id;
        this.model = model;
        this.created = created;
        this.object = object;
        this.systemFingerPrint = systemFingerPrint;
        this.usage = usage;
        this.choices = choices;
    }

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("model")
    public String getModel() {
        return this.model;
    }

    @JsonProperty("created")
    public int getCreated() {
        return this.created;
    }

    @JsonProperty("object")
    public String getObject() {
        return this.object;
    }

    @JsonProperty("system_fingerprint")
    public String getSystemFingerPrint() {
        return this.systemFingerPrint;
    }

    @JsonProperty("usage")
    public Usage getUsage() {
        return this.usage;
    }

    @JsonProperty("choices")
    public List<Choice> getChoices() {
        return this.choices;
    }
}
