package com.maxmayev.autograph.services.rest;

import com.maxmayev.autograph.domain.Message;
import com.maxmayev.autograph.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RestServiceImpl implements RestService {

    MessageRepository messageRepository;

    @Autowired
    public RestServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveOrUpdateMessage(Message message, Long id) {
        return messageRepository.findById(id).map(x -> {
            x.setName(message.getName());
            x.setAutoNumber(message.getAutoNumber());
            x.setMessText(message.getMessText());
            return messageRepository.save(x);
        }).orElseGet(() -> {
            message.setId(id);
            return messageRepository.save(message);
        });
    }

    @Override
    public void deleteConsumer(Long id) {
        messageRepository.deleteById(id);
    }
}
