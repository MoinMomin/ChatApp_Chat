package com.chatApp.chatApp.Controller;

import com.chatApp.chatApp.mapper.ChatMapper;
import com.chatApp.chatApp.model.Messages;
import com.chatApp.chatApp.service.MessageService;
import com.chatApp.chatApp.service.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    MessageService chatService;
    @PostMapping("/createchat")
    public ResponseEntity<Map> signUp(@RequestBody Messages chat) {
        if (chat != null && (chat.getUsername() == null || chat.getUsername() == ""  || chat.getMessage() == null || chat.getMessage() == "")) {
            return CustomResponse.badRequest("request is not proper");
        }

        chatService.createChat(chat);
        return CustomResponse.ok(chat);
    }
    @GetMapping("/getMessagelist/{userName}")
    public ResponseEntity<Map> getChatListByUserName(@PathVariable String userName,@RequestParam(defaultValue = "0") int skip,
                                                     @RequestParam(defaultValue = "10") int limit){
        ChatMapper chatMapper=new ChatMapper();
        chatMapper.setUserName(userName);
        chatMapper.setSkip(skip);
        chatMapper.setLimit(limit);
  return CustomResponse.ok(chatService.getChatByUserName(chatMapper));
    }
}
