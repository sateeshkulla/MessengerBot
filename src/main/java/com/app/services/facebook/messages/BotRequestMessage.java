package com.app.services.facebook.messages;

import java.util.ArrayList;
import java.util.List;

import com.app.models.Event;
import com.app.models.Message;
import com.app.models.Messaging;
import com.app.models.Postback;

public class BotRequestMessage {
    private String object;
    List<Event> entry = new ArrayList<Event>();

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Event> getEntry() {
        return entry;
    }

    public void setEntry(List<Event> entry) {
        this.entry = entry;
    }
    
    public String getSenderId(){
        for(Event event : entry){
            for (Messaging message : event.getMessaging()){
                return message.getSender().getId();
            }
        }
        return null;
    }
    
    public String getRecipientId(){
        for(Event event : entry){
            for (Messaging message : event.getMessaging()){
                return message.getRecipient().getId();
            }
        }
        return null;
    }
    
    public Postback getPayload(){
        for(Event event : entry){
            for (Messaging message : event.getMessaging()){
                return message.getPostback();
            }
        }
        return null;
    }
    
    public Message getMessage(){
        for(Event event : entry){
            for (Messaging message : event.getMessaging()){
                return message.getMessage();
            }
        }
        return null;
    }

}
