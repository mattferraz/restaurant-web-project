package com.tads.mhsf.restaurant.repositories;

import com.tads.mhsf.restaurant.exceptions.RepositoryException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void create(T t) throws RepositoryException;
    Optional<T> read(int id) throws RepositoryException;
    void update(T t) throws RepositoryException;
    void delete(int id) throws RepositoryException;
    List<T> readAll() throws RepositoryException;

}
