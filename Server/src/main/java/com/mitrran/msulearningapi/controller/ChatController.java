package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.model.Message;
import com.mitrran.msulearningapi.repository.MessageRepository;
import com.mitrran.msulearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    // GET /api/chat/{senderId}/{receiverId} — fetch chat history between two users
    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getChatMessages(
            @PathVariable Long senderId,
            @PathVariable Long receiverId) {

        String sender = String.valueOf(senderId);
        String receiver = String.valueOf(receiverId);
        List<Message> messages = messageRepository.findChatMessages(sender, receiver);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // POST /api/chat — send a new message
    @PostMapping("")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        message.setTimestamp(new Date());
        Message saved = messageRepository.save(message);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}