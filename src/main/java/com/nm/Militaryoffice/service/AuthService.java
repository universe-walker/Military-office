package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService(@Autowired UserService userService) {
        this.userService = userService;
    }

    public boolean isUserAuthored(HttpSession session) {
        Long userId = (Long) (session.getAttribute("userId") == null ? null : session.getAttribute("userId"));
        if (userId == null) {
            return false;
        }
        User userById = userService.findUserById(userId);
        return userById != null;
    }
}
