package com.example.chat_spring_thymeleaf.service;

import com.example.chat_spring_thymeleaf.entity.User;
import com.example.chat_spring_thymeleaf.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationService {

    private final UserRepository userRepository;

    public NotificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void manage(List<User> users, User currentUser) {
        for (User user : users) {
            int notificationCount = userRepository.getNotificationCount(currentUser.getId(), user.getId());
            user.setUnread(notificationCount);
        }
    }
}
