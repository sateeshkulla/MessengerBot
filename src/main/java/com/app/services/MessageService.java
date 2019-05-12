package com.app.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.models.User;
import com.app.services.facebook.messages.BotRequestMessage;
import com.app.services.facebook.messages.Button;
import com.app.services.facebook.messages.Element;
import com.app.services.facebook.messages.SimpleMessage;

@Service
public class MessageService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value( "${facebook_graph_base_url}" )
    private String graphUrl;
    
    @Value( "${facebook_page_access_token}" )
    private String pageAccessToken;
    
    public void processMessage(BotRequestMessage message){
        if(message.getPayload() != null &&
                (message.getPayload().getPayload().equals("offers") 
                || message.getPayload().getPayload().equals("price"))) {
           emailConfirmationMessage(message);
           return;
        }
        if(message.getMessage() != null && EmailValidator.getInstance().isValid(message.getMessage().getText())){
            SimpleMessage finalMessage = new SimpleMessage();
            finalMessage.setRecipientId(message.getSenderId());
            finalMessage.setMessageText("We sent offers to your email provided");
            finalMessage.getMessage().setAttachment(null);//TODO
            restTemplate.postForEntity(messagesUrl(), finalMessage, String.class);
            return;
        }
        postBackMessage(message);
    }
    
    private User getUser(BotRequestMessage message){
        return restTemplate.getForObject(userInfoUrl(message.getSenderId()), User.class);
    }
    
    private void sendMessage(BotRequestMessage message) {
        User user = getUser(message);
        SimpleMessage welcomeMessage = new SimpleMessage();
        welcomeMessage.setRecipientId(message.getSenderId());
        welcomeMessage.setMessageText("Hi! " + user.getFirstName() + " " + user.getLastName());
        welcomeMessage.getMessage().setAttachment(null);
        restTemplate.postForEntity(messagesUrl(), welcomeMessage, String.class);
    }
    
    private void emailConfirmationMessage(BotRequestMessage message){
        SimpleMessage emailMessage = new SimpleMessage();
        emailMessage.setRecipientId(message.getSenderId());
        emailMessage.setMessageText("Please Confirm Email");
        emailMessage.setEmailContentType("user_email");
        emailMessage.getMessage().setAttachment(null);
        restTemplate.postForEntity(messagesUrl(), emailMessage, String.class);
    }
    
    private void postBackMessage(BotRequestMessage message){
        SimpleMessage postbackMessage = new SimpleMessage();
        postbackMessage.setRecipientId(message.getSenderId());
        
        User user = getUser(message);
        
        Element element = new Element();
        element.setTitle("Hi! " + user.getFirstName() + " " + user.getLastName() + ", Welcome to TrueBuying");
        element.setImageUrl("https://static.tcimg.net/vehicles/primary/dfb65a418bb4d8a0/2019-Porsche-718_Boxster-red-full_color-driver_side_front_quarter.png");
        
        Button offerButton = new Button();
        offerButton.setPayload("offers");
        offerButton.setTitle("View Offers");
        element.addButtons(offerButton);
        
        Button priceButton = new Button();
        priceButton.setPayload("price");
        priceButton.setTitle("View Price");
        element.addButtons(priceButton);
        
        postbackMessage.getMessage().getAttachment().getPayload().getElements().add(element);
        
        restTemplate.postForEntity(messagesUrl(), postbackMessage, String.class);
    }
    
    private String messagesUrl(){
        return graphUrl + "me/messages?access_token=" + pageAccessToken;
    }
    
    private String userInfoUrl(String senderId){
        return graphUrl + senderId + "?access_token=" + pageAccessToken;
    }

}
