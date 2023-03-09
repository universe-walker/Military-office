package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.User;
import com.nm.Militaryoffice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void save(User user) {
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Optional<Long> checkUserCanSignInAndGetId(User user) {
        Optional<User> userOptional = userRepository.findUserById(user.getId());
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        User dbUser = userOptional.get();
        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return Optional.of(dbUser.getId());
        } else {
            return Optional.empty();
        }
    }

    public User findUserById(@NotNull Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
