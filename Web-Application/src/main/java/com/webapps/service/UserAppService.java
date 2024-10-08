package com.webapps.service;

import com.webapps.api.entity.User;
import com.webapps.api.repository.UserRepository;
import com.webapps.model.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {

    @Autowired
    private UserRepository userRepository;

    public ModelUser getByUsername(String username) {
        User user = userRepository.findFirstByUsername(username);
        return toModelUser(user);
    }

    private ModelUser toModelUser(User user) {
        return ModelUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .token(user.getToken())
                .tokenExpiredAt(user.getTokenExpiredAt()).build();
    }
}
