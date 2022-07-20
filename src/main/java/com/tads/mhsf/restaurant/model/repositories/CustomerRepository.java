package com.tads.mhsf.restaurant.model.repositories;

import com.tads.mhsf.restaurant.model.dao.ConnectionManager;
import com.tads.mhsf.restaurant.model.entities.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<Customer, String>{

    @Override
    public void create(Customer customer) {
        String sql = "insert into cliente(cpf, name, phone_number, email, password) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            pstm.setString(1, customer.getCpf());
            pstm.setString(2, customer.getName());
            pstm.setString(3, customer.getPhoneNumber());
            pstm.setString(4, customer.getEmail());
            pstm.setString(5, customer.getPassword());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer read(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf=?";
        return getCustomer(cpf, sql);
    }

    public Customer readFromEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email=?";
        return getCustomer(email, sql);
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE cliente SET name=?, phone_number=?, email=?, password=? WHERE cpf=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            pstm.setString(1, customer.getName());
            pstm.setString(2, customer.getPhoneNumber());
            pstm.setString(3, customer.getEmail());
            pstm.setString(4, customer.getPassword());
            pstm.setString(5, customer.getCpf());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, cpf);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> readAll() {
        String sql = "SELECT * FROM cliente";
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Customer customer = createCustomerFromQuery(result);
                customers.add(customer);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    private Customer createCustomerFromQuery(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setCpf(result.getString("cpf"));
        customer.setName(result.getString("name"));
        customer.setPhoneNumber(result.getString("phone_number"));
        customer.setEmail(result.getString("email"));
        customer.setPassword(result.getString("password"));
        return customer;
    }

    private Customer getCustomer(String whereFilter, String sql) {
        Customer customer = null;
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, whereFilter);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                customer = createCustomerFromQuery(result);
            }

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

}
