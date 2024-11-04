package com.selenium.seleniumdemotest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WIAM
 **/
@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Render the login.html template
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        if ("user".equals(username) && "password".equals(password)) {
            return "redirect:/dashboard"; // Successful login
        }
        model.addAttribute("error", "Invalid credentials"); // Error handling
        return "login"; // Return to login page
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";  // Render the dashboard.html template
    }
}

