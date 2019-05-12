package com.app.services.controllers.webhooks.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.MessageService;
import com.app.services.facebook.messages.BotRequestMessage;

@RestController
@RequestMapping("/webhooks/facebook/bot")
public class BotController {
    
    @Value( "${facebook_webhook_verify_token}" )
    private String token;
    
    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam(value = "hub.verify_token") String verifyToken,
            @RequestParam(value = "hub.challenge") String challenge) {
        if(verifyToken != null && verifyToken.equals(token)){
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Token");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> notifyMessages(@RequestBody BotRequestMessage message) { 
        //@RequestBody String pBody to get plain message
        messageService.processMessage(message);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
