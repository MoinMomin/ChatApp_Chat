package com.chatApp.chatApp.service;

import com.chatApp.chatApp.mapper.ChatMapper;
import com.chatApp.chatApp.model.Chat;
import com.chatApp.chatApp.repository.ChatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceimpl implements ChatService{
    @Autowired
    ChatDAO chatDAO;
    @Override
    public Chat createChat(Chat chat) {
        return chatDAO.save(chat);
    }

    @Override
    public List<Chat> getChatByUserName(ChatMapper chatMapper) {
        Pageable pageable = PageRequest.of(chatMapper.getPage(), chatMapper.getSize(), Sort.by("id").descending());
        return chatDAO.findByUserName(chatMapper.getUserName(),pageable);
    }
}
