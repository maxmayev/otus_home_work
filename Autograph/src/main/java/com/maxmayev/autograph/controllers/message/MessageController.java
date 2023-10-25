package com.maxmayev.autograph.controllers.message;

import com.maxmayev.autograph.domain.Message;
import com.maxmayev.autograph.services.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/lk")
@SessionAttributes("user")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendmessage")
    public String sendMessage(@ModelAttribute Message message, SessionStatus sessionStatus){
        messageService.sendMessage(message,sessionStatus);
        return "redirect:/lk";
    }

    @PostMapping("/delete")
    public String deleteMessage(String id){
        messageService.deleteMessage(id);
        return "redirect:/lk";
    }

    @GetMapping
    public String showOrderForm(Model model, Authentication authentication){
        messageService.showLkForm(model, authentication);
        return "lk";
    }
}