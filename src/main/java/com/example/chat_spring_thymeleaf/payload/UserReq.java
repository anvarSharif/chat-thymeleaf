package com.example.chat_spring_thymeleaf.payload;

import lombok.Data;

@Data
public class UserReq {
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private Integer userId;
}
