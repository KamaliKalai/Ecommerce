package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String welcomePage() { return "welcome"; }

    @GetMapping("/login")
    public String userLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user, HttpSession session, Model model) {
        User existingUser = userService.authenticate(user.getUsername(), user.getPassword());
        if(existingUser == null) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
        session.setAttribute("user", existingUser);
        if("ADMIN".equals(existingUser.getRole())) return "redirect:/admin/dashboard";
        else return "redirect:/user/dashboard";
    }

    @GetMapping("/register")
    public String userRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user, Model model) {
        if(!userService.registerUser(user)) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
