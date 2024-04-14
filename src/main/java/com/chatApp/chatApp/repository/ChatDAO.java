package com.chatApp.chatApp.repository;

import com.chatApp.chatApp.model.Messages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDAO extends MongoRepository<Messages,Long> {
    public List<Messages> findByUsernameOrderByDateDesc(String username,Pageable pageable);
}
