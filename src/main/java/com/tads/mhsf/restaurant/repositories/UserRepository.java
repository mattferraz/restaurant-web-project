package com.tads.mhsf.restaurant.repositories;

import com.tads.mhsf.restaurant.dao.ConnectionManager;
import com.tads.mhsf.restaurant.entities.User;
import com.tads.mhsf.restaurant.entities.UserType;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User>{

    public static Integer userLoggedInID = null;

    @Override
    public void create(User user) throws RepositoryException {
        String sql = "INSERT INTO app_user(cpf, name, phone_number, email, password, type) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            pstm.setString(1, user.getCpf());
            pstm.setString(2, user.getName());
            pstm.setString(3, user.getPhoneNumber());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getPassword());
            pstm.setString(6, user.getUserType().name().toLowerCase());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public Optional<User> read(int id) throws RepositoryException {
        String sql = "SELECT * FROM app_user WHERE id=?";
        User user = null;
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                user = createCustomerFromQuery(result);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return Optional.ofNullable(user);
    }

    public Optional<User> readFromEmail(String email) throws RepositoryException {
        String sql = "SELECT * FROM app_user WHERE email=?";
        User user = null;
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, email);

            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                user = createCustomerFromQuery(result);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) throws RepositoryException {
        String sql = "UPDATE app_user SET name=?, phone_number=?, email=?, password=? WHERE cpf=?";
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPhoneNumber());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getPassword());
            pstm.setString(5, user.getCpf());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void delete(int id) throws RepositoryException {
        String sql = "DELETE FROM app_user WHERE id=?";
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
    public List<User> readAll() throws RepositoryException {
        String sql = "SELECT * FROM app_user";
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                User user = createCustomerFromQuery(result);
                users.add(user);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RepositoryException();
        }

        return users;
    }

    private User createCustomerFromQuery(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getInt("id"));
        user.setCpf(result.getString("cpf"));
        user.setName(result.getString("name"));
        user.setPhoneNumber(result.getString("phone_number"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        user.setUserType(UserType.valueOf(result.getString("type").toUpperCase()));
        return user;
    }

}
