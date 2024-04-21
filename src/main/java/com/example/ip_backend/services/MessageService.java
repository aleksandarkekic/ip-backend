package com.example.ip_backend.services;

import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Message;

import java.util.List;

public interface MessageService extends CrudService<Integer> {
    Message sendMessage(String text, Integer id) throws NotFoundException;
    List<Message> getChat(Integer id);
}
