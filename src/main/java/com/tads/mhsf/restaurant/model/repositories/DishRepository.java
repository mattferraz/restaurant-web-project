package com.tads.mhsf.restaurant.model.repositories;

import com.tads.mhsf.restaurant.model.dao.ConnectionManager;
import com.tads.mhsf.restaurant.model.entities.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishRepository implements Repository<Dish, Integer>{

    @Override
    public void create(Dish dish) {
        String sql = "INSERT INTO prato(name, description, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            pstm.setString(1, dish.getName());
            pstm.setString(2, dish.getDescription());
            pstm.setDouble(3, dish.getPrice());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Dish read(Integer id) {
        String sql = "SELECT * FROM prato WHERE id=?";
        Dish dish = new Dish();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                dish.setId(id);
                dish.setName(result.getString("name"));
                dish.setDescription(result.getString("description"));
                dish.setPrice(result.getDouble("price"));
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dish;
    }

    @Override
    public void update(Dish dish) {
        String sql = "UPDATE prato SET name=?, description=?, price=? WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, dish.getName());
            pstm.setString(2, dish.getDescription());
            pstm.setDouble(3, dish.getPrice());
            pstm.setInt(4, dish.getId());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM prato WHERE id=?";
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
    public List<Dish> readAll() {
        String sql = "SELECT * FROM prato";
        List<Dish> dishes = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Dish dish = new Dish();
                dish.setId(result.getInt("id"));
                dish.setName(result.getString("name"));
                dish.setDescription(result.getString("description"));
                dish.setPrice(result.getDouble("price"));
                dishes.add(dish);
            }

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dishes;
    }

}
