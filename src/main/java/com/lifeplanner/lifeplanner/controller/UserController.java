package com.lifeplanner.lifeplanner.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.lifeplanner.lifeplanner.model.User;
import com.lifeplanner.lifeplanner.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, hashedPassword);

        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/?login=success";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}