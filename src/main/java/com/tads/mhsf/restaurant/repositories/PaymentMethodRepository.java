package com.tads.mhsf.restaurant.repositories;

import com.tads.mhsf.restaurant.dao.ConnectionManager;
import com.tads.mhsf.restaurant.entities.PaymentMethod;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class PaymentMethodRepository implements Repository<PaymentMethod>{

    @Override
    public void create(PaymentMethod paymentMethod) throws RepositoryException {
        String sql = "INSERT INTO payment_method(name) VALUES (?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, paymentMethod.getName());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public Optional<PaymentMethod> read(int id) throws RepositoryException {
        String sql = "SELECT * FROM payment_method WHERE id=?";
        PaymentMethod paymentMethod = null;
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                paymentMethod = new PaymentMethod();
                paymentMethod.setId(id);
                paymentMethod.setName(result.getString("name"));
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return Optional.ofNullable(paymentMethod);
    }

    @Override
    public void update(PaymentMethod paymentMethod) throws RepositoryException {
        String sql = "UPDATE payment_method SET name=? WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, paymentMethod.getName());
            pstm.setInt(2, paymentMethod.getId());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void delete(int id) throws RepositoryException {
        String sql = "DELETE FROM payment_method WHERE id=?";
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
    public List<PaymentMethod> readAll() throws RepositoryException {
        String sql = "SELECT * FROM payment_method";
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(result.getInt("id"));
                paymentMethod.setName(result.getString("name"));
                paymentMethods.add(paymentMethod);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return paymentMethods;
    }

}
