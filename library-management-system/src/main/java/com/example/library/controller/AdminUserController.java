package com.example.library.controller;

import com.example.library.entity.User;
import com.example.library.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.library.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {

        List<User> users = adminUserService.getAllUsers();

        model.addAttribute("users", users);

        return "admin-users";
    }

    @PostMapping("/admin/users/{id}/toggle")
    public String toggleUserStatus(
            @PathVariable Long id) {

        adminUserService.toggleUserStatus(id);

        return "redirect:/admin/users";
    }
    
    @GetMapping("/admin/users/{id}/edit")
    public String editUser(
            @PathVariable Long id,
            Model model) {

        User user = adminUserService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", User.Role.values());

        return "admin-user-edit";
    }

    @PostMapping("/admin/users/{id}/edit")
    public String updateUser(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam User.Role role,
            Model model) {

        try {

            adminUserService.updateUser(
                    id,
                    name,
                    email,
                    role
            );

            return "redirect:/admin/users?updated";

        } catch (IllegalStateException | IllegalArgumentException e) {

            User user = adminUserService.getUserById(id);

            model.addAttribute("user", user);
            model.addAttribute("roles", User.Role.values());
            model.addAttribute("error", e.getMessage());

            return "admin-user-edit";
        }
    }
    
    @PostMapping("/admin/users/{id}/delete")
    public String deleteUser(
            @PathVariable Long id) {

        try {
            adminUserService.deleteUser(id);

            return "redirect:/admin/users?deleted";

        } catch (IllegalStateException | IllegalArgumentException e) {

            return "redirect:/admin/users?error="
                    + java.net.URLEncoder.encode(
                            e.getMessage(),
                            java.nio.charset.StandardCharsets.UTF_8
                    );
        }
    }
}