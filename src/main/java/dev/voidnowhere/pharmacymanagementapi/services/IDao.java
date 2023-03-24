package dev.voidnowhere.pharmacymanagementapi.services;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    T update(T t);

    void delete(T t);
}
