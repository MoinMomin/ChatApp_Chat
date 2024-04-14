package com.chatApp.chatApp.service;

import com.chatApp.chatApp.mapper.ChatMapper;
import com.chatApp.chatApp.model.Messages;
import com.chatApp.chatApp.repository.ChatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    ChatDAO chatDAO;
    @Override
    public Messages createChat(Messages chat) {
        return chatDAO.save(chat);
    }

    @Override
    public List<Messages> getChatByUserName(ChatMapper chatMapper) {
        Pageable pageable = PageRequest.of(chatMapper.getSkip(), chatMapper.getLimit());
        return chatDAO.findByUsernameOrderByDateDesc(chatMapper.getUserName(), pageable);
    }
}
