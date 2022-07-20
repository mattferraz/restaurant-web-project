package com.tads.mhsf.restaurant.model.repositories;

import com.tads.mhsf.restaurant.model.dao.ConnectionManager;
import com.tads.mhsf.restaurant.model.entities.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order, Integer>{

    @Override
    public void create(Order order) {
        String sql = "INSERT INTO pedido(id_client, id_prato, id_pagamento, date_time, price, note) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, order.getCustomer().getCpf());
            pstm.setInt(2, order.getDish().getId());
            pstm.setInt(3, order.getPaymentMethod().getId());
            pstm.setTimestamp(4, Timestamp.valueOf(order.getDateTime()));
            pstm.setDouble(5, order.getPrice());
            pstm.setString(6, order.getNote());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order read(Integer id) {
        String sql = "SELECT * FROM pedido WHERE id=?";
        Order order = new Order();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                order = createOrderFromQuery(result);
            }

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE pedido SET date_time=?, price=?, note=? WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setTimestamp(1, Timestamp.valueOf(order.getDateTime()));
            pstm.setDouble(2, order.getPrice());
            pstm.setString(3, order.getNote());
            pstm.setInt(4, order.getId());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM pedido WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> readAll() {
        String sql = "SELECT * FROM pedido";
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                Order order = createOrderFromQuery(result);
                orders.add(order);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public List<Order> readAll(String customerID) {
        String sql = "SELECT * FROM pedido WHERE id_client=?";
        List<Order> customerOrders = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, customerID);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                Order order = createOrderFromQuery(result);
                customerOrders.add(order);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerOrders;
    }

    private Order createOrderFromQuery(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setId(result.getInt("id"));
        order.setCustomer(new CustomerRepository().read(result.getString("id_client")));
        order.setDish(new DishRepository().read(result.getInt("id_prato")));
        order.setPaymentMethod(new PaymentMethodRepository().read(result.getInt("id_pagamento")));
        order.setDateTime(result.getTimestamp("date_time").toLocalDateTime());
        order.setPrice(result.getDouble("price"));
        order.setNote(result.getString("note"));
        return order;
    }

}
