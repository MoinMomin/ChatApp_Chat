package com.chatApp.chatApp.service;

import com.chatApp.chatApp.mapper.ChatMapper;
import com.chatApp.chatApp.model.Messages;

import java.util.List;

public interface MessageService {
    public Messages createChat(Messages chat);
    public List<Messages> getChatByUserName(ChatMapper chatMapper);
}
