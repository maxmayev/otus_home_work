package com.maxmayev.autograph.controllers.security;

import com.maxmayev.autograph.dto.RegistrationForm;
import com.maxmayev.autograph.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/register")
@SessionAttributes("user")
public class RegistrationController {

    UserRepository userRepository;
    PasswordEncoder encoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }


    @PostMapping
    public String processRegistration(@ModelAttribute("register") RegistrationForm form, BindingResult result, Model model) {

        Pattern pattern = Pattern.compile("^\\w\\d{3}\\w{2}\\d{2,3}$", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(form.getUsername());
        if (!matcher.find() || !form.getPassword().equals(form.getConfirm())) {
            model.addAttribute("error", true);
            return "registration";
        }
        if (userRepository.findByUsername(form.getUsername()) != null) {
            model.addAttribute("error", true);
            return "registration";
        }
        userRepository.save(form.toUser(encoder));
        return "redirect:/login";
    }


}
