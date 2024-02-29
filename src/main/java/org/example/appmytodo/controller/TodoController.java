package org.example.appmytodo.controller;

import jakarta.validation.Valid;
import org.example.appmytodo.payload.*;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/todo")
public interface TodoController {
    @GetMapping
    HttpEntity<ApiResponse<List<TodoGroupDto>>> all();
    @PostMapping
    HttpEntity<ApiResponse<TodoDto>> add(@Valid @RequestBody TodoCreateDto todoCreateDto);

    @PutMapping("/{id}")
    HttpEntity<ApiResponse<TodoDto>> edit(@PathVariable String id,
                                          @Valid @RequestBody TodoUpdateDto todoUpdateDto);

    @PatchMapping("/{id}")
    HttpEntity<ApiResponse<TodoDto>> changeCompleted(@PathVariable String id);

    @DeleteMapping("/{id}")
    HttpEntity<ApiResponse<Object>> delete(@PathVariable String id);
}
