package com.example.chat_spring_thymeleaf.component;

import com.example.chat_spring_thymeleaf.entity.User;
import com.example.chat_spring_thymeleaf.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final UserRepository userRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String update;

    @Override
    public void run(String... args) throws Exception {
        if (update.equals("create")) {
            User user1=new User("eshmat1","toshmatov1","1","1");
            User user2=new User("eshmat2","toshmatov2","2","2");
            User user3=new User("eshmat3","toshmatov3","3","3");
            User user4=new User("eshmat4","toshmatov4","4","4");
            User user5=new User("eshmat5","toshmatov5","5","5");
            userRepository.saveAll(List.of(user1,user2,user3,user4,user5));
        }
    }
}
