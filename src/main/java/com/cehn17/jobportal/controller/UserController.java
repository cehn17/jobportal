package com.cehn17.jobportal.controller;

import com.cehn17.jobportal.entity.User;
import com.cehn17.jobportal.entity.UserType;
import com.cehn17.jobportal.services.UserService;
import com.cehn17.jobportal.services.UserTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserTypeService userTypeService;
    private final UserService userService;

    @Autowired
    public UserController(
            UserTypeService userTypeService,
            UserService userService) {

        this.userTypeService = userTypeService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UserType> userTypes = userTypeService.findAll();
        model.addAttribute("getAllTypes", userTypes);
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid User user, Model model) {

        Optional<User> optionalUsers = userService.getUserByEmail(user.getEmail());
        if (optionalUsers.isPresent()) {
            model.addAttribute("error", "Email already registered,try to login or register with other email.");
            List<UserType> userTypes = userTypeService.findAll();
            model.addAttribute("getAllTypes", userTypes);
            model.addAttribute("user", new User());
            return "register";
        }
        userService.addNew(user);

        return "redirect:/dashboard/";
    }

    /*@GetMapping("/dashboard")
    public String showDashboard() {

        return "dashboard"; // sin redirect, esto busca dashboard.html en templates
    }*/

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

}
