package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.entities.UserType;
import com.tads.mhsf.restaurant.exceptions.DataNotFoundException;
import com.tads.mhsf.restaurant.entities.User;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", model.getAttribute("msg"));
        return "index";
    }

    @RequestMapping("/adm/home")
    public String admHome(Model model) {
        model.addAttribute("msg", model.getAttribute("msg"));
        return "adm_home";
    }

    @RequestMapping("/validate/login")
    public String findUserByLoginData(RedirectAttributes redirectAttributes, String email, String password) {
        try {
            User user = userService.findUserByEmailAndPassword(email, password);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/signin";
        } catch (DataNotFoundException | RepositoryException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:/";
        }
    }

    @RequestMapping("/signin")
    public String customerSignIn(RedirectAttributes redirectAttributes, @ModelAttribute User user) {
        try {
            userService.signIn(user);
            if (user.getUserType().equals(UserType.CUSTOMER)) {
                return "redirect:/dishes";
            } else {
                return "redirect:/adm/home";
            }
        } catch (DataNotFoundException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:/";
        }
    }

    @RequestMapping("/signup")
    public String registerUserForm() {
        return "signup";
    }

    @RequestMapping("/signup/redirect")
    public String registerUser(RedirectAttributes redirectAttributes, User user) {
        try {
            user.setUserType(UserType.CUSTOMER);
            userService.signUp(user);
            redirectAttributes.addFlashAttribute("user",user);
            return "redirect:/signin";
        } catch (RepositoryException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:/";
        }
    }

    @RequestMapping("/customers")
    public String customers(Model model) {
        try {
            model.addAttribute("customers", userService.findAllCustomers());
        } catch (RepositoryException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "customers";
    }
}
