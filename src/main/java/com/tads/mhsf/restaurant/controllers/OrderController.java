package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.entities.Order;
import com.tads.mhsf.restaurant.exceptions.DataNotFoundException;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class OrderController {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/orders")
    public String orders(Model model) {
        try {
            List<Order> orders = orderService.findAllOrders();
            model.addAttribute("orders", orders);
            model.addAttribute("dateTimeFormatter", formatter);
            model.addAttribute("currencyFormatter", currencyFormatter);
        } catch (RepositoryException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "orders";
    }

    @RequestMapping("/order/{orderID}")
    public String order(Model model, @PathVariable("orderID") int orderID) {
        try {
            Order order = orderService.findOrderByID(orderID);
            model.addAttribute("order", order);
            model.addAttribute("dateTimeFormatter", formatter);
            model.addAttribute("currencyFormatter", currencyFormatter);
        } catch (DataNotFoundException | RepositoryException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "order";
    }

    @RequestMapping("/my/orders")
    public String customerOrders(Model model) {
        try {
            List<Order> customerOrders = orderService.findAllOrdersFromCustomerLoggedIn();
            model.addAttribute("customerOrders", customerOrders);
            model.addAttribute("dateTimeFormatter", formatter);
            model.addAttribute("currencyFormatter", currencyFormatter);
        } catch (RepositoryException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "customer_orders";
    }

    @RequestMapping("/order/{dishID}/confirm")
    public String confirmOrder(RedirectAttributes redirectAttributes,
                               @PathVariable int dishID,
                               int paymentID,
                               String note) {
        try {
            orderService.createOrder(dishID, paymentID, note);
            redirectAttributes.addFlashAttribute("msg", "Order placed successfully!");
        } catch (DataNotFoundException | RepositoryException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:/dishes";
    }
}
