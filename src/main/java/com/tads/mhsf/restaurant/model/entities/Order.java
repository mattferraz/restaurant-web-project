package com.tads.mhsf.restaurant.model.entities;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private Customer customer;
    private Dish dish;
    private PaymentMethod paymentMethod;
    private LocalDateTime dateTime;
    private Double price;
    private String note;

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
