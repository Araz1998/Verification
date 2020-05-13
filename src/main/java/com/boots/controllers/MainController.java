package com.boots.controllers;

import com.boots.Repos.MessageRepo;
import com.boots.domain.Message;
import com.boots.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//@Controller
//public class MainController {
//
//    @Autowired
//    private MessageRepo messasgeRepo;
//
//    @GetMapping("/")
//    public String greeting(Model model) {
//
//        return "greeting";
//    }
//
//    @GetMapping("/main")
//    public String main(Model model){
//        Iterable<Message> messages = messasgeRepo.findAll();
//        model.addAttribute("mess",messages);
//        return "main";
//    }
//
//    @PostMapping("/main")
//    public String add (@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Model model){
//        Message message =new Message(text, tag, user);
//        messasgeRepo.save(message);
//        Iterable<Message> messages = messasgeRepo.findAll();
//        model.addAttribute("mess",messages);
//        return "main";
//    }
//
//    @PostMapping("filter")
//    public String filter(@RequestParam String text, Model model){
//        Iterable<Message> messages;
//        if(text!=null && !text.isEmpty()) {
//            messages = messasgeRepo.findByTag(text);
//        }else {
//            messages = messasgeRepo.findAll();
//        }
//        model.addAttribute("mess", messages);
//        return "main";
//    }
//}

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Model model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);

        return "main";
    }
}