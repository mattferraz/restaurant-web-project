package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.model.entities.Dish;
import com.tads.mhsf.restaurant.model.repositories.DishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DishController {

    @RequestMapping("/dishes")
    public String dishes(Model model) {
        List<Dish> dishes = new DishRepository().readAll();
        model.addAttribute("dishes", dishes);
        return "dishes";
    }

    @RequestMapping("/registerDish")
    public String registerDish(Model model, Dish dish) {
        new DishRepository().create(dish);
        return "redirect:/home";
    }

    @RequestMapping("/registerDish/form")
    public String registerDishForm(Model model) {
        return "register_dish";
    }

}
