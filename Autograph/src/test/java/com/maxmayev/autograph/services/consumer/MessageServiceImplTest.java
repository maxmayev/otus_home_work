/*
package com.maxmayev.autograph.services.consumer;

import com.maxmayev.autograph.domain.Message;
import com.maxmayev.autograph.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.support.SessionStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MessageServiceImplTest {


    @MockBean
    MessageRepository messageRepository;

    @MockBean
    SessionStatus sessionStatus;

    @Autowired
    MessageService messageService;

    @Captor
    ArgumentCaptor<Message> argumentCaptor;

    @Test
    void saveConsumerTest() {
        Message message = new Message();
        when(messageRepository.save(message)).thenReturn(message);
        messageService.sendMessage(message,sessionStatus);
        verify(messageRepository,times(1)).save(argumentCaptor.capture());
        assertEquals(message,argumentCaptor.getValue());

    }
}*/
