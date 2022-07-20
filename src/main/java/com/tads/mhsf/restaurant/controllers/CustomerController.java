package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.model.entities.Customer;
import com.tads.mhsf.restaurant.model.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {

    @RequestMapping("/customers")
    public String customers(Model model) {
        List<Customer> customers = new CustomerRepository().readAll();
        model.addAttribute("customers", customers);

        return "customers";
    }

    @RequestMapping("/signup")
    public String registerCustomerForm(Model model) {
        return "signup";
    }

    @RequestMapping("/signup/redirect")
    public String registerCustomer(Model model, Customer customer) {
        new CustomerRepository().create(customer);
        RestaurantController.userLoggedIn = customer;

        return "redirect:/dishes";
    }
}
