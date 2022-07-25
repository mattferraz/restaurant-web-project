package com.tads.mhsf.restaurant.repositories;

import com.tads.mhsf.restaurant.dao.ConnectionManager;
import com.tads.mhsf.restaurant.entities.Order;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<Order>{

    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public OrderRepository(UserRepository userRepository,
                           DishRepository dishRepository,
                           PaymentMethodRepository paymentMethodRepository) {
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public void create(Order order) throws RepositoryException {
        String sql = "INSERT INTO app_order(id_user, id_dish, id_payment, date_time, price, note) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, order.getUser().getId());
            pstm.setInt(2, order.getDish().getId());
            pstm.setInt(3, order.getPaymentMethod().getId());
            pstm.setTimestamp(4, Timestamp.valueOf(order.getDateTime()));
            pstm.setDouble(5, order.getPrice());
            pstm.setString(6, order.getNote());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public Optional<Order> read(int id) throws RepositoryException {
        String sql = "SELECT * FROM app_order WHERE id=?";
        Order order = null;
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                order = createOrderFromQuery(result);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return Optional.ofNullable(order);
    }

    @Override
    public void update(Order order) throws RepositoryException {
        String sql = "UPDATE app_order SET date_time=?, price=?, note=? WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setTimestamp(1, Timestamp.valueOf(order.getDateTime()));
            pstm.setDouble(2, order.getPrice());
            pstm.setString(3, order.getNote());
            pstm.setInt(4, order.getId());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void delete(int id) throws RepositoryException {
        String sql = "DELETE FROM app_order WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public List<Order> readAll() throws RepositoryException {
        String sql = "SELECT * FROM app_order";
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
            throw new RepositoryException();
        }

        return orders;
    }

    public List<Order> readAll(int customerID) throws RepositoryException {
        String sql = "SELECT * FROM app_order WHERE id_user=?";
        List<Order> customerOrders = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, customerID);

            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                Order order = createOrderFromQuery(result);
                customerOrders.add(order);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return customerOrders;
    }

    private Order createOrderFromQuery(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setId(result.getInt("id"));
        order.setUser(userRepository.read(result.getInt("id_user")).orElse(null));
        order.setDish(dishRepository.read(result.getInt("id_dish")).orElse(null));
        order.setPaymentMethod(paymentMethodRepository.read(result.getInt("id_payment")).orElse(null));
        order.setDateTime(result.getTimestamp("date_time").toLocalDateTime());
        order.setPrice(result.getDouble("price"));
        order.setNote(result.getString("note"));
        return order;
    }

}
