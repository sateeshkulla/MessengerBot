package com.app.services.facebook.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReply {
    
    @JsonProperty("content_type")
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
