package com.chatApp.chatApp.service;

import com.chatApp.chatApp.model.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
public class WebSocketHandler extends TextWebSocketHandler {
    @Autowired
    MessageService messageService;

    private static List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Broadcast the received message to all connected sessions
        try {
            log.info("Total Connected Device " + sessions.size());


            ObjectMapper objectMapper=new ObjectMapper();
            Messages messages=   objectMapper.readValue(message.getPayload(),Messages.class);

            messageService.createChat(messages);
            for (WebSocketSession s : sessions) {
                // Check if the session is still open
                if (s.isOpen()) {
                    log.info(s.getLocalAddress() + " BROADCAST STARTED");
                    log.info(message.getPayload() + " BROADCAST MESSAGE ");
                    s.sendMessage(message);
                    log.info(s.getUri() + " BROADCAST END");
                } else {
                    sessions.remove(s); // Optionally remove the closed session from the list
                }
            }
            log.info("Total Connected Device After" + sessions.size());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Remove the closed session from the list
        sessions.remove(session);
}

}