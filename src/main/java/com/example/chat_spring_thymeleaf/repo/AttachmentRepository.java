package com.example.chat_spring_thymeleaf.repo;

import com.example.chat_spring_thymeleaf.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}