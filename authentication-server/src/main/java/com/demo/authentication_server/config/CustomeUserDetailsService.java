package com.demo.authentication_server.config;

import com.demo.authentication_server.model.entity.UserInfo;
import com.demo.authentication_server.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userRepository; // Assuming you have a UserRepository to fetch user details
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo= userRepository.findByUserName(username);
        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserInfo user = userInfo.get();
        return new CustomUserDetails(user.getUserName(), user.getPassword(), user.getRoles());
    }
}
