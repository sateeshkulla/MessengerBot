package com.app.services.facebook.messages;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

    @JsonProperty("template_type")
    private String templateType = "generic";

    @JsonProperty("elements")
    private List<Element> elements = new ArrayList<>();

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
