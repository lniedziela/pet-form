package com.softserveinc.model.user;

import com.softserveinc.model.user.dto.UserRequest;
import com.softserveinc.model.user.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserRequest userRequest) {
        return new User(
                userRequest.getId(),
                userRequest.getUsername(),
                userRequest.getPassword()
        );
    }

    public UserResponse map(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}
