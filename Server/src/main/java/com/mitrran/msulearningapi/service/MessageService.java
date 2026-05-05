package com.mitrran.msulearningapi.service;

import com.mitrran.msulearningapi.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final List<Message> chatMessages = new ArrayList<>();


    public List<Message> getChatMessages(String sender, String receiver) {
        return chatMessages.stream()
                .filter(message ->
                        (message.getSender().equals(sender) && message.getReceiver().equals(receiver))
                                || (message.getSender().equals(receiver) && message.getReceiver().equals(sender)))
                .collect(Collectors.toList());
    }

    public Message saveMessage(Message message) {
        message.setTimestamp(new Date());
        chatMessages.add(message);
        return message;
    }
}
