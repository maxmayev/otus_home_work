package com.maxmayev.autograph.services.message;

import com.maxmayev.autograph.domain.Message;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

public interface MessageService {
   // Boolean addConsumer(Message message, Errors errors);
    void sendMessage(Message message, SessionStatus sessionStatus);
    void deleteMessage(String id);
    void showLkForm(Model model, Authentication authentication);

}
