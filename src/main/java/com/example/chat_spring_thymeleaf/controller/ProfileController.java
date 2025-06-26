package com.example.chat_spring_thymeleaf.controller;

import com.example.chat_spring_thymeleaf.entity.Attachment;
import com.example.chat_spring_thymeleaf.entity.AttachmentContent;
import com.example.chat_spring_thymeleaf.entity.User;
import com.example.chat_spring_thymeleaf.payload.UserReq;
import com.example.chat_spring_thymeleaf.repo.AttachmentContentRepository;
import com.example.chat_spring_thymeleaf.repo.AttachmentRepository;
import com.example.chat_spring_thymeleaf.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class ProfileController {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private final UserRepository userRepository;

    @PostMapping("/update")
    public String getEditeUserPage(@ModelAttribute UserReq userReq, @RequestParam MultipartFile photo, HttpSession session) throws IOException {

        Attachment attachment;
        if (!photo.isEmpty()) {
            attachment = new Attachment(photo.getOriginalFilename());
            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    photo.getBytes(),
                    attachment
            );
            attachmentContentRepository.save(attachmentContent);
        }else {
            User user = userRepository.findById(userReq.getUserId()).get();
            attachment=user.getPersonalPhoto();
        }

        User user = new User(
                userReq.getUserId(),
                userReq.getFirstName(),
                userReq.getLastName(),
                userReq.getPhone(),
                userReq.getPassword(),
                attachment
        );
        userRepository.save(user);
        session.setAttribute("currentUser",user);
        return "redirect:/chat";
    }
}
