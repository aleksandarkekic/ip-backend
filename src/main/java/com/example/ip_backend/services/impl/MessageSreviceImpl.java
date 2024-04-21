package com.example.ip_backend.services.impl;

import com.example.ip_backend.base.CrudJpaService;
import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Message;
import com.example.ip_backend.models.entities.MessageEntity;
import com.example.ip_backend.models.entities.UserEntity;
import com.example.ip_backend.models.requests.MessageRequest;
import com.example.ip_backend.repositories.MessageEntityRepository;
import com.example.ip_backend.services.MessageService;
import com.example.ip_backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageSreviceImpl extends CrudJpaService<MessageEntity, Integer> implements MessageService {

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final MessageEntityRepository messageEntityRepository;

    public MessageSreviceImpl(ModelMapper modelMapper, UserService userService, MessageEntityRepository messageEntityRepository) {
        super(messageEntityRepository,modelMapper,MessageEntity.class);
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.messageEntityRepository = messageEntityRepository;
    }


    @Override
    public Message sendMessage(String text, Integer id) throws NotFoundException {
        MessageRequest message=new MessageRequest();
        message.setText(text);
        message.setSenderId(userService.getCurrentId());
        message.setReceiverId(id);
        if(userService.ifRoleIsAdvisor(id)){
            message.setAdvisor(true);
        }
        message.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        message.setChatId(userService.getCurrentId()+"#"+id);
        insert(message,MessageEntity.class);
        return null;
    }

    @Override
    public List<Message> getChat(Integer id) {
        return messageEntityRepository.findChat(userService.getCurrentId(),id).stream()
                .map(m->modelMapper.map(m,Message.class))
                .collect(Collectors.toList());
    }
}
