package com.example.chat_spring_thymeleaf.entity;

import com.example.chat_spring_thymeleaf.entity.abs.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;
    private String text;
    @OneToOne
    private Attachment file;
    @CreationTimestamp
    private LocalDateTime dateTime;

    private boolean hasRead;

    public Message(User fromUser, User toUser, String text,Attachment attachment) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.text = text;
        this.file=attachment;
    }

    public String getDateTime() {
        return dateTime.format(
                DateTimeFormatter.ofPattern("HH:mm")
        );
    }
}
