package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.model.entities.Customer;
import com.tads.mhsf.restaurant.model.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;


@Controller
public class RestaurantController {
    static Customer userLoggedIn = null;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping("/home")
    public String admHome(Model model) {
        return "adm_home";
    }

    @RequestMapping("/signin")
    public String signInForm(Model model) {
        return "signin";
    }

    @RequestMapping("/signin/checkinfo")
    public String signIn(Model model, String email, String password) {
        if (email.equalsIgnoreCase("adm") && password.equals("adm")) {
            return "redirect:/home";
        }
        Customer customer = new CustomerRepository().readFromEmail(email);

        boolean wasUserFound = customer != null && customer.getPassword().equals(password);
        if (wasUserFound) {
            userLoggedIn = customer;
            return "redirect:/dishes";
        }

        model.addAttribute("msg", "Email ou senha incorretos.");
        return "index";
    }

}
