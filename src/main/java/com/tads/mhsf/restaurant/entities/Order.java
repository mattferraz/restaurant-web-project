package com.tads.mhsf.restaurant.entities;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private User user;
    private Dish dish;
    private PaymentMethod paymentMethod;
    private LocalDateTime dateTime;
    private Double price;
    private String note;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Dish getDish() {
        return dish;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Double getPrice() {
        return price;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
