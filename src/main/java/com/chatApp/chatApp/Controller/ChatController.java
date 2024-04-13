package com.chatApp.chatApp.Controller;

import com.chatApp.chatApp.mapper.ChatMapper;
import com.chatApp.chatApp.model.Chat;
import com.chatApp.chatApp.service.ChatService;
import com.chatApp.chatApp.service.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatService chatService;
    @PostMapping("/createchat")
    public ResponseEntity<Map> signUp(@RequestBody Chat chat) {
        if (chat != null && (chat.getUserName() == null || chat.getUserName() == ""  || chat.getMessage() == null || chat.getMessage() == "")) {
            return CustomResponse.badRequest("request is not proper");
        }

        chatService.createChat(chat);
        return CustomResponse.ok(chat);
    }
    @GetMapping("/getMessagelist/{userName}")
    public ResponseEntity<Map> getChatListByUserName(@PathVariable String userName,@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size){
        ChatMapper chatMapper=new ChatMapper();
        chatMapper.setUserName(userName);
        chatMapper.setPage(page);
        chatMapper.setSize(size);
  return CustomResponse.ok(chatService.getChatByUserName(chatMapper));
    }
}
