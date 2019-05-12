package com.app.services.facebook.messages;

import com.app.models.Message;
import com.app.models.Recipient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMessage {
    
    @JsonProperty("recipient")
    private Recipient recipient;
    
    @JsonProperty("message")
    private Message message = new Message();

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    
    public void setRecipientId(String recipientId) {
        this.recipient = new Recipient();
        this.recipient.setId(recipientId);
    }
    
    public void setMessageText(String text) {
        this.message.setText(text);
    }
    
    public void setEmailContentType(String contentType) {
        QuickReply reply = new QuickReply();
        reply.setContentType(contentType);
        this.message.addEmailMessage(reply);
    }

}
