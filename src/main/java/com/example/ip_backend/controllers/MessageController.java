package com.example.ip_backend.controllers;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Message;
import com.example.ip_backend.models.requests.MessageRequest;
import com.example.ip_backend.models.requests.MessageRequestFromFront;
import com.example.ip_backend.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat/{id}")
    public List<Message> getChat(@PathVariable Integer id){
        return messageService.getChat(id);
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody MessageRequestFromFront messageRequest) throws NotFoundException {
        messageService.sendMessage(messageRequest.getText(),messageRequest.getReceiverId());
    }
}
