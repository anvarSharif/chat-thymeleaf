package com.example.chat_spring_thymeleaf.repo;

import com.example.chat_spring_thymeleaf.entity.Attachment;
import com.example.chat_spring_thymeleaf.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    Optional<AttachmentContent> findByAttachment(Attachment attachment);
}