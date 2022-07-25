package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.entities.Dish;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.services.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping("/dishes")
    public String dishes(Model model) {
        try {
            model.addAttribute("dishes", dishService.findAllDishes());
            model.addAttribute("currencyFormatter", OrderController.currencyFormatter);
        } catch (RepositoryException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "dishes";
    }

    @RequestMapping("/registerDish")
    public String registerDish(Model model, Dish dish) {
        try {
            dishService.createDish(dish);
        } catch (RepositoryException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "redirect:/adm/home";
    }

    @RequestMapping("/registerDish/form")
    public String registerDishForm(Model model) {
        return "register_dish";
    }

}
