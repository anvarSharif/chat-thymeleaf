package com.example.chat_spring_thymeleaf.controller;

import com.example.chat_spring_thymeleaf.entity.User;
import com.example.chat_spring_thymeleaf.payload.LoginReq;
import com.example.chat_spring_thymeleaf.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public String authenticate(@ModelAttribute LoginReq loginReq, HttpServletRequest request){
        Optional<User> optionalUser = userRepository.findByPhoneAndPassword(loginReq.getPhone(), loginReq.getPassword());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            request.getSession().setAttribute("currentUser",user);
            return "redirect:/chat";
        }else {
            return "redirect:/login";
        }
    }
    @PostMapping("/logout")
    public String Logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
