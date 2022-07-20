package com.tads.mhsf.restaurant.model.repositories;

import com.tads.mhsf.restaurant.model.dao.ConnectionManager;
import com.tads.mhsf.restaurant.model.entities.PaymentMethod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRepository implements Repository<PaymentMethod, Integer>{

    @Override
    public void create(PaymentMethod paymentMethod) {
        String sql = "INSERT INTO forma_pagamento(description) VALUES (?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, paymentMethod.getDescription());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PaymentMethod read(Integer id) {
        String sql = "SELECT * FROM forma_pagamento WHERE id=?";
        PaymentMethod paymentMethod = new PaymentMethod();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                paymentMethod.setId(id);
                paymentMethod.setDescription(result.getString("description"));
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentMethod;
    }

    @Override
    public void update(PaymentMethod paymentMethod) {
        String sql = "UPDATE forma_pagamento SET description=? WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, paymentMethod.getDescription());
            pstm.setInt(2, paymentMethod.getId());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM forma_pagamento WHERE id=?";
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
    public List<PaymentMethod> readAll() {
        String sql = "SELECT * FROM forma_pagamento";
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(result.getInt("id"));
                paymentMethod.setDescription(result.getString("description"));
                paymentMethods.add(paymentMethod);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentMethods;
    }

}
