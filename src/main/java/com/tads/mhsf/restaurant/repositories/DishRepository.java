package com.tads.mhsf.restaurant.repositories;

import com.tads.mhsf.restaurant.entities.Dish;
import com.tads.mhsf.restaurant.dao.ConnectionManager;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class DishRepository implements Repository<Dish>{

    @Override
    public void create(Dish dish) throws RepositoryException {
        String sql = "INSERT INTO dish(name, description, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            pstm.setString(1, dish.getName());
            pstm.setString(2, dish.getDescription());
            pstm.setDouble(3, dish.getPrice());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public Optional<Dish> read(int id) throws RepositoryException {
        String sql = "SELECT * FROM dish WHERE id=?";
        Dish dish = null;
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                dish = new Dish();
                dish.setId(id);
                dish.setName(result.getString("name"));
                dish.setDescription(result.getString("description"));
                dish.setPrice(result.getDouble("price"));
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return Optional.ofNullable(dish);
    }

    @Override
    public void update(Dish dish) throws RepositoryException {
        String sql = "UPDATE dish SET name=?, description=?, price=? WHERE id=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, dish.getName());
            pstm.setString(2, dish.getDescription());
            pstm.setDouble(3, dish.getPrice());
            pstm.setInt(4, dish.getId());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void delete(int id) throws RepositoryException {
        String sql = "DELETE FROM dish WHERE id=?";
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
    public List<Dish> readAll() throws RepositoryException {
        String sql = "SELECT * FROM dish";
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
            throw new RepositoryException();
        }

        return dishes;
    }

}
