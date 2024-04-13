package com.chatApp.chatApp.service;

import com.chatApp.chatApp.mapper.ChatMapper;
import com.chatApp.chatApp.model.Chat;

import java.util.List;

public interface ChatService {
    public Chat createChat(Chat chat);
    public List<Chat> getChatByUserName(ChatMapper chatMapper);
}
