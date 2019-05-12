package com.app.services.facebook.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * attachment: { 
 *      type: 'template', 
 *      payload: { 
 *      template_type: 'generic',
 *      elements: [
 *          { 
 *              title: <TITLE>, 
 *              buttons: [ 
 *                  { 
 *                      type: 'postback', 
 *                      title: <BUTTON_TITLE>, 
 *                      payload: <PAYLOAD_TYPE> 
 *                   } 
 *               ] 
 *          }
 *     ] 
 *     } 
 * }
 */
public class Attachment {

    @JsonProperty("type")
    private String type = "template";
    
    @JsonProperty("payload")
    private Payload payload = new Payload();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
    
    
}
