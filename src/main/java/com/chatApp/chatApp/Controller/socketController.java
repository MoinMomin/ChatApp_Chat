package com.chatApp.chatApp.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class socketController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public socketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/chat") // Endpoint to receive messages
    @SendTo("/topic/messages") // Send message to this topic
    public Message sendMessage(Message message) {
        log.info("-=======--------------========"+message);
        return message;
    }

}
