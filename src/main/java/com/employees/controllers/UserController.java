package com.employees.controllers;

import com.employees.models.Position;
import com.employees.models.UserA;
import com.employees.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author SamuelBoada
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserA());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserA user) {
        userService.registerUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("/{userId}/positions")
    public ResponseEntity<List<Position>> getUserPositions(@PathVariable Integer userId) {
        UserA user = userService.getUserById(userId);
        List<Position> positions = userService.getPositionsForUser(user);
        return ResponseEntity.ok(positions);
    }

}