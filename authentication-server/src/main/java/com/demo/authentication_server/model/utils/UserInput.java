package com.demo.authentication_server.model.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String roles;
}
