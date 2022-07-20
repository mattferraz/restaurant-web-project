package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.model.entities.Dish;
import com.tads.mhsf.restaurant.model.entities.Order;
import com.tads.mhsf.restaurant.model.entities.PaymentMethod;
import com.tads.mhsf.restaurant.model.repositories.DishRepository;
import com.tads.mhsf.restaurant.model.repositories.OrderRepository;
import com.tads.mhsf.restaurant.model.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {

    @RequestMapping("/orders")
    public String orders(Model model) {
        List<Order> orders = new OrderRepository().readAll();
        model.addAttribute("orders", orders);
        model.addAttribute("dateTimeFormatter", RestaurantController.formatter);
        model.addAttribute("currencyFormatter", RestaurantController.currencyFormatter);
        return "orders";
    }

    @RequestMapping("/order/{orderID}")
    public String order(Model model, @PathVariable("orderID") int orderID) {
        Order order = new OrderRepository().read(orderID);
        model.addAttribute("order", order);
        model.addAttribute("dateTimeFormatter", RestaurantController.formatter);
        model.addAttribute("currencyFormatter", RestaurantController.currencyFormatter);
        return "order";
    }

    @RequestMapping("/my/orders")
    public String customerOrders(Model model) {
        List<Order> customerOrders = new OrderRepository().readAll(RestaurantController.userLoggedIn.getCpf());
        model.addAttribute("customerOrders", customerOrders);
        model.addAttribute("dateTimeFormatter", RestaurantController.formatter);
        model.addAttribute("currencyFormatter", RestaurantController.currencyFormatter);
        return "customer_orders";
    }

    @RequestMapping("/order/{dishID}/confirm")
    public String confirmOrder(Model model, @PathVariable("dishID") int dishID, int paymentID, String note) {
        Order order = new Order();

        order.setCustomer(RestaurantController.userLoggedIn);
        order.setPaymentMethod(new PaymentMethodRepository().read(paymentID));
        order.setDateTime(LocalDateTime.now());
        order.setNote(note);

        Dish dish = new DishRepository().read(dishID);
        order.setDish(dish);
        order.setPrice(dish.getPrice());

        new OrderRepository().create(order);

        model.addAttribute("msg", "Pedido realizado com sucesso!");

        return "redirect:/dishes";
    }
}
