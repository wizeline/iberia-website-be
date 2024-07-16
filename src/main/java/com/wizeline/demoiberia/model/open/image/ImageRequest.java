package com.wizeline.demoiberia.model.open.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeName("ImageRequest")
public class ImageRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -1482118277764194028L;

    private final String prompt;
    private final String model;
    private final int n;
    private final String quality;
    private final String style;
    private final String size;

    @JsonCreator
    public ImageRequest(final String prompt, final String model, final int n, final String quality, final String style, final String size) {
        this.prompt = prompt;
        this.model = model;
        this.n = n;
        this.quality = quality;
        this.style = style;
        this.size = size;
    }

    @JsonProperty("prompt")
    public String getPrompt() {
        return this.prompt;
    }

    @JsonProperty("model")
    public String getModel() {
        return this.model;
    }

    @JsonProperty("n")
    public int getN() {
        return this.n;
    }

    @JsonProperty("quality")
    public String getQuality() {
        return this.quality;
    }

    @JsonProperty("style")
    public String getStyle() {
        return this.style;
    }

    @JsonProperty("size")
    public String getSize() {
        return this.size;
    }
}
