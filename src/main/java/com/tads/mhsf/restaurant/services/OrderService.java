package com.tads.mhsf.restaurant.services;

import com.tads.mhsf.restaurant.entities.Dish;
import com.tads.mhsf.restaurant.entities.Order;
import com.tads.mhsf.restaurant.exceptions.DataNotFoundException;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.repositories.OrderRepository;
import com.tads.mhsf.restaurant.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final DishService dishService;
    private final PaymentMethodService paymentMethodService;

    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        DishService dishService,
                        PaymentMethodService paymentMethodService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.dishService = dishService;
        this.paymentMethodService = paymentMethodService;
    }

    public Order findOrderByID(int id) throws DataNotFoundException, RepositoryException {
        return orderRepository
                .read(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found."));
    }

    public List<Order> findAllOrders() throws RepositoryException {
        return orderRepository.readAll();
    }

    public List<Order> findAllOrdersFromCustomerLoggedIn() throws RepositoryException {
        return orderRepository.readAll(UserRepository.userLoggedInID);
    }

    public void createOrder(int dishID, int paymentID, String note) throws DataNotFoundException, RepositoryException {
        Order order = new Order();
        order.setUser(userService.findUserByID(UserRepository.userLoggedInID));
        order.setPaymentMethod(paymentMethodService.findPaymentMethodByID(paymentID));
        order.setDateTime(LocalDateTime.now());
        order.setNote(note);

        Dish dish = dishService.findDishByID(dishID);
        order.setDish(dish);
        order.setPrice(dish.getPrice());

        orderRepository.create(order);
    }

}
