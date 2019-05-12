package com.app.models;

import java.util.ArrayList;
import java.util.List;

import com.app.services.facebook.messages.Attachment;
import com.app.services.facebook.messages.QuickReply;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String text;
    
    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;
    
    @JsonProperty("attachment")
    private Attachment attachment = new Attachment();
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
    }
    
    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
    
    public void addEmailMessage(QuickReply reply) {
        quickReplies = new ArrayList<>();
        quickReplies.add(reply);
    }
    
}
