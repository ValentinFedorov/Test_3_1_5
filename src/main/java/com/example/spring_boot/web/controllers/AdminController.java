package com.example.spring_boot.web.controllers;

import com.example.spring_boot.web.model.User;
import com.example.spring_boot.web.service.RoleService;
import com.example.spring_boot.web.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/main")
    public String showAllUsers(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("authUser",user);
        model.addAttribute("allRoleList", roleService.getListRoles());
        model.addAttribute("newuser", new User());
        return "/admin/admin";
    }

    @PostMapping("/main")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/main";
    }

    @PatchMapping("/admin/{id}/updateuser")
    public String updateUser(@PathVariable("id") long id, User user) {
        userService.updateUser(user, id);
        return "redirect:/admin/main";
    }

    @DeleteMapping("/admin/{id}/deleteuser")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/main";
    }
}
