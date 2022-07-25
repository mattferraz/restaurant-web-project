package com.tads.mhsf.restaurant.services;

import com.tads.mhsf.restaurant.exceptions.DataNotFoundException;
import com.tads.mhsf.restaurant.entities.User;
import com.tads.mhsf.restaurant.entities.UserType;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByID(int id) throws DataNotFoundException, RepositoryException {
        return userRepository
                .read(id)
                .orElseThrow(() -> new DataNotFoundException("User not found."));
    }

    public void signUp(User user) throws RepositoryException {
        userRepository.create(user);
    }

    public void signIn(User user) throws DataNotFoundException {
        UserRepository.userLoggedInID = user.getId();
    }

    public User findUserByEmailAndPassword(String email, String password) throws DataNotFoundException, RepositoryException {
        User user = userRepository
                .readFromEmail(email)
                .orElseThrow(() -> new DataNotFoundException("The email you entered doesn't belong to an account. " +
                        "Please check your email and try again."));
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new DataNotFoundException("Sorry, the password was incorrect. " +
                    "Please check your password and try again.");
        }
    }

    public List<User> findAllCustomers() throws RepositoryException {
        return userRepository
                .readAll()
                .stream()
                .filter(user -> user.getUserType().equals(UserType.CUSTOMER))
                .collect(Collectors.toList());
    }

    // Adding service layer, improving single responsibility and handling exceptions.

}
