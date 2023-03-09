package com.nm.Militaryoffice.controller;

import com.nm.Militaryoffice.model.User;
import com.nm.Militaryoffice.model.UserRole;
import com.nm.Militaryoffice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    private void createUserManually() {
        User newUser = new User(
                "qwerty",
                "nmotlich@voenkom.ru",
                "qwerty",
                false,
                UserRole.MILITARY_COMMISSAR
        );
        newUser.setName("Никита");
        newUser.setPatronymic("Васильевич");
        newUser.setSurname("Мотлич");
        userService.save(newUser);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("isAdmin");
        return "redirect:/publications";
    }

    @GetMapping("/")
    @PostMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleResourceNotFoundException() {
//        return "error/notfound";
//    }
}
