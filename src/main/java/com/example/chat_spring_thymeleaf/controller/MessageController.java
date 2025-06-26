package com.example.chat_spring_thymeleaf.controller;

import com.example.chat_spring_thymeleaf.entity.Attachment;
import com.example.chat_spring_thymeleaf.entity.AttachmentContent;
import com.example.chat_spring_thymeleaf.entity.Message;
import com.example.chat_spring_thymeleaf.entity.User;
import com.example.chat_spring_thymeleaf.repo.AttachmentContentRepository;
import com.example.chat_spring_thymeleaf.repo.AttachmentRepository;
import com.example.chat_spring_thymeleaf.repo.MessageRepository;
import com.example.chat_spring_thymeleaf.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Transactional
    @PostMapping("/send")
    public String send(@RequestParam String text, @RequestParam Integer toUser, @SessionAttribute User currentUser, @RequestParam MultipartFile file) throws IOException {
        Message message = new Message(
                currentUser,
                userRepository.findById(toUser).get(),
                text,
                saveAttachment(file)
        );
        messageRepository.save(message);
        return "redirect:/chat?userId=" + toUser;
    }

    private Attachment saveAttachment(MultipartFile file) throws IOException {
        if (file != null) {
            Attachment attachment = new Attachment(
                    file.getOriginalFilename()
            );
            attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent(
                    file.getBytes(),
                    attachment
            );
            attachmentContentRepository.save(attachmentContent);
            return attachment;
        } else {
            return null;
        }
    }

}
