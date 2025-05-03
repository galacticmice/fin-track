package com.ft.fin_track.controller;

import com.ft.fin_track.database.User;
import com.ft.fin_track.database.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = UserDAO.authenticateUser(username, password);
        
        if (user != null) {
            // Store user in session
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        // Check if username already exists
        User existingUser = UserDAO.authenticateUser(username, password);
        if (existingUser != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        // Create new user
        User newUser = new User(username, email, password);
        boolean success = UserDAO.addUser(newUser);
        
        if (success) {
            model.addAttribute("success", "Registration successful. Please login.");
            return "login";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}