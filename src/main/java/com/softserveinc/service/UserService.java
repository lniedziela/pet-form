package com.softserveinc.service;

import com.softserveinc.model.user.Role;
import com.softserveinc.model.user.UserMapper;
import com.softserveinc.model.user.dto.UserRequest;
import com.softserveinc.model.user.dto.UserResponse;
import com.softserveinc.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
    }

    @Transactional
    public Optional<UserResponse> createUser(UserRequest userRequest) {
        if (userRepository.existsByUsernameIgnoreCase(userRequest.getUsername())) {
            return Optional.empty();
        } else {
            var user = userMapper.map(userRequest);
            user.setRole(Role.EMPLOYEE);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            var savedUser = userRepository.save(user);
            return Optional.of(userMapper.map(savedUser));
        }
    }
}
