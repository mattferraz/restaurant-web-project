package com.tads.mhsf.restaurant.model.repositories;

import java.util.List;

public interface Repository<T, I> {

    void create(T t);
    T read(I i);
    void update(T t);
    void delete(I i);
    List<T> readAll();

}
