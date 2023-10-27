package com.maxmayev.autograph.services.consumer;

import com.maxmayev.autograph.domain.Message;

public interface ConsumerService {
    Message saveOrUpdateMessage(Message message, Long id);
    void deleteConsumer(Long id);
}
