package com.example.chat_spring_thymeleaf.entity;

import com.example.chat_spring_thymeleaf.entity.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private String phone;
    private String password;
    @ManyToOne
    private Attachment personalPhoto;
    @Transient
    private Integer unread;
    public String getFullName(){
        return firstName+" "+lastName;
    }

    public User(String firstName, String lastName, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
    }

    public User(Integer id, String firstName, String lastName, String phone, String password, Attachment personalPhoto) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.personalPhoto = personalPhoto;
    }
}
