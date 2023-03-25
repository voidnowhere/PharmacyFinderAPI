package dev.voidnowhere.pharmacymanagementapi.services.v1;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDao<T, ID> {
    default ResponseEntity<List<T>> findAll() {
        return ResponseEntity.badRequest().build();
    }

    default ResponseEntity<T> findById(ID id) {
        return ResponseEntity.badRequest().build();
    }

    default ResponseEntity<?> save(T t) {
        return ResponseEntity.badRequest().build();
    }

    default ResponseEntity<?> save(ID id, T t) {
        return ResponseEntity.badRequest().build();
    }

    ResponseEntity<?> update(T t);

    default ResponseEntity<Void> delete(ID id) {
        return ResponseEntity.badRequest().build();
    }
}
