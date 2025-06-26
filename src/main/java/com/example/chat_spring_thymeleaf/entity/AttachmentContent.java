package com.example.chat_spring_thymeleaf.entity;

import com.example.chat_spring_thymeleaf.entity.abs.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent extends BaseEntity {
    private byte[] content;
    @OneToOne
    private Attachment attachment;
}
