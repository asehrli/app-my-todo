package org.example.appmytodo.controller;

import lombok.RequiredArgsConstructor;
import org.example.appmytodo.payload.*;
import org.example.appmytodo.service.TodoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoControllerImpl implements TodoController {
    private final TodoService todoService;

    @Override
    public HttpEntity<ApiResponse<List<TodoGroupDto>>> all() {
        return ResponseEntity.ok(todoService.all());
    }

    @Override
    public HttpEntity<ApiResponse<TodoDto>> add(TodoCreateDto todoCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.add(todoCreateDto));
    }

    @Override
    public HttpEntity<ApiResponse<TodoDto>> edit(String id, TodoUpdateDto todoUpdateDto) {
        return ResponseEntity.accepted().body(todoService.edit(id, todoUpdateDto));
    }


    @Override
    public HttpEntity<ApiResponse<TodoDto>> changeCompleted(String id) {
        return ResponseEntity.accepted().body(todoService.changeCompleted(id));
    }

    @Override
    public HttpEntity<ApiResponse<Object>> delete(String id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
