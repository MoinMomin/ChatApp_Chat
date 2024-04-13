package com.chatApp.chatApp.repository;

import com.chatApp.chatApp.model.Chat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDAO extends MongoRepository<Chat,Long> {
    public List<Chat> findByUserName(String userName, Pageable pageable);
}
