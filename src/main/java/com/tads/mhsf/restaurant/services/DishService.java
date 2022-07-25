package com.tads.mhsf.restaurant.services;

import com.tads.mhsf.restaurant.entities.Dish;
import com.tads.mhsf.restaurant.exceptions.DataNotFoundException;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish findDishByID(int id) throws DataNotFoundException, RepositoryException {
        return dishRepository
                .read(id)
                .orElseThrow(() -> new DataNotFoundException("Prato não encontrado."));
    }

    public void createDish(Dish dish) throws RepositoryException {
        dishRepository.create(dish);
    }

    public List<Dish> findAllDishes() throws RepositoryException {
        return dishRepository.readAll();
    }

}
