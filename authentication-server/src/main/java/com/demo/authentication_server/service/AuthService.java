package com.demo.authentication_server.service;

import com.demo.authentication_server.model.entity.UserInfo;
import com.demo.authentication_server.model.utils.AuthRequest;
import com.demo.authentication_server.model.utils.UserInput;
import com.demo.authentication_server.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String SaveUser(UserInput user){
        UserInfo newUser= new UserInfo();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setRoles(user.getRoles());
        userRepository.save(newUser);
        return ("User " + user.getUserName() + " saved successfully.");
    }

    public String GenerateToken(AuthRequest user) {
            return jwtService.generateToken(user.getUserName());
    }

    public String RefreshToken(String token) {
        jwtService.validateToken(token);
        String userName = jwtService.getUsername(token);
        return jwtService.generateToken(userName);
    }
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
