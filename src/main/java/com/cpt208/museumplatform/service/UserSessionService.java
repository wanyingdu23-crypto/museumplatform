package com.cpt208.museumplatform.service;

import com.cpt208.museumplatform.dto.LoginRequest;
import com.cpt208.museumplatform.dto.RegisterRequest;
import com.cpt208.museumplatform.dto.UpdateProfileRequest;
import com.cpt208.museumplatform.entity.UserEntity;
import com.cpt208.museumplatform.model.User;
import com.cpt208.museumplatform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSessionService {

    private final UserRepository userRepository;

    public UserSessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegisterRequest request) {
        String username = normalize(request.username());
        String email = normalize(request.email());
        String password = safeValue(request.password());
        String confirmPassword = safeValue(request.confirmPassword());

        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Please complete all fields.");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new IllegalArgumentException("This username already exists. Please choose another one.");
        }
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("This email already exists. Please use another one.");
        }

        UserEntity savedUser = userRepository.save(new UserEntity(
            username,
            email,
            password,
            LocalDateTime.now()
        ));
        return toModel(savedUser);
    }

    public User login(LoginRequest request) {
        String account = normalize(request.account());
        String password = safeValue(request.password());
        UserEntity user = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(account, account)
            .orElseThrow(() -> new IllegalArgumentException("Account not found."));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password.");
        }
        return toModel(user);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId).map(this::toModel);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public User updateProfile(Long userId, UpdateProfileRequest request) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found."));

        String username = normalize(request.username());
        String password = safeValue(request.password());
        String confirmPassword = safeValue(request.confirmPassword());

        if (!username.isBlank() && !username.equalsIgnoreCase(user.getUsername())) {
            if (userRepository.existsByUsernameIgnoreCase(username)) {
                throw new IllegalArgumentException("This username already exists. Please choose another one.");
            }
            user.setUsername(username);
        }

        if (!password.isBlank() || !confirmPassword.isBlank()) {
            if (password.isBlank() || confirmPassword.isBlank()) {
                throw new IllegalArgumentException("Please complete the password fields.");
            }
            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match.");
            }
            user.setPassword(password);
        }

        return toModel(userRepository.save(user));
    }

    private String normalize(String value) {
        return safeValue(value).trim();
    }

    private String safeValue(String value) {
        return value == null ? "" : value;
    }

    private User toModel(UserEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getPassword());
    }
}
