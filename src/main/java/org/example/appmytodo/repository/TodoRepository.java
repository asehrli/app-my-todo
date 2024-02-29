package org.example.appmytodo.repository;

import org.example.appmytodo.document.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface TodoRepository extends MongoRepository<Todo, String> {
    boolean existsByTitleAndCreatedAtIsGreaterThan(String title, LocalDateTime time);
    boolean existsByTitleAndCreatedAtIsGreaterThanAndIdNot(String title, LocalDateTime createdAt, String id);
}
