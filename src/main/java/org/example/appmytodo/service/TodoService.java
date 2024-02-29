package org.example.appmytodo.service;

import org.example.appmytodo.payload.*;

import java.util.List;

public interface TodoService {
    ApiResponse<List<TodoGroupDto>> all();
    ApiResponse<TodoDto> add(TodoCreateDto todoCreateDto);
    ApiResponse<TodoDto> edit(String id, TodoUpdateDto todoUpdateDto);
    void delete(String id);

    ApiResponse<TodoDto> changeCompleted(String id);

}
