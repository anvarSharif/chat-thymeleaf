package com.example.chat_spring_thymeleaf.controller;

import com.example.chat_spring_thymeleaf.entity.Attachment;
import com.example.chat_spring_thymeleaf.entity.AttachmentContent;
import com.example.chat_spring_thymeleaf.repo.AttachmentContentRepository;
import com.example.chat_spring_thymeleaf.repo.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @GetMapping("/download/{attachmentId}")
    public void Download(@PathVariable Integer attachmentId, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new RuntimeException("file not found"));
        AttachmentContent content = attachmentContentRepository.findByAttachment(attachment).orElseThrow(() -> new RuntimeException("file not found"));
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getFileName()+"\"");
        response.getOutputStream().write(content.getContent());
    }
    @GetMapping("/get/{attachmentId}")
    public void get(@PathVariable Integer attachmentId, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new RuntimeException("file not found"));
        AttachmentContent content = attachmentContentRepository.findByAttachment(attachment).orElseThrow(() -> new RuntimeException("file not found"));
        response.getOutputStream().write(content.getContent());
    }
}













