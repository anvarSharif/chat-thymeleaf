package com.example.chat_spring_thymeleaf.controller;

import com.example.chat_spring_thymeleaf.entity.Message;
import com.example.chat_spring_thymeleaf.entity.User;
import com.example.chat_spring_thymeleaf.repo.MessageRepository;
import com.example.chat_spring_thymeleaf.repo.UserRepository;
import com.example.chat_spring_thymeleaf.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final NotificationService notificationService;

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/chat")
    public String chat(Model model, @RequestParam(required = false) Integer userId, @SessionAttribute User currentUser) {

        List<User> users = userRepository.findAll();
        users.removeIf(item -> item.getId().equals(currentUser.getId()));
        notificationService.manage(users,currentUser);
        model.addAttribute("users", users);
        model.addAttribute("currentUser",currentUser);
        if (userId != null) {
            model.addAttribute("selectedUser",
                    users.stream().filter(item -> item.getId().equals(userId)).findFirst().get());
            List<Message> chatHistory = messageRepository.getChatHistory(currentUser.getId(), userId);
            for (Message message : chatHistory) {
                if (!message.isHasRead()&&!message.getFromUser().getId().equals(currentUser.getId())){
                    message.setHasRead(true);
                }
            }
            messageRepository.saveAll(chatHistory);
            model.addAttribute("messages", chatHistory);

        }
        return "chat";
    }

    @GetMapping("/user/edite")
    public String getEditeUserPage(Model model,@SessionAttribute User currentUser){
        model.addAttribute("user", currentUser);
        return "/editeProfile";
    }
}
