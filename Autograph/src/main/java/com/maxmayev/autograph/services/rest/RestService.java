package com.maxmayev.autograph.services.rest;

import com.maxmayev.autograph.domain.Message;

public interface RestService {
    Message saveOrUpdateMessage(Message message, Long id);
    void deleteConsumer(Long id);
}
