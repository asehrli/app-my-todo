package org.example.appmytodo.service;

import lombok.RequiredArgsConstructor;
import org.example.appmytodo.document.Todo;
import org.example.appmytodo.exceptions.MyConflictException;
import org.example.appmytodo.exceptions.MyNotFoundException;
import org.example.appmytodo.mapper.TodoMapper;
import org.example.appmytodo.payload.*;
import org.example.appmytodo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    public ApiResponse<List<TodoGroupDto>> all() {
        return ApiResponse.success(todoRepository
                .findAll()
                .stream()
                .sorted()
                .map(todoMapper::template)
                .collect(Collectors.groupingBy(TodoServiceImpl::thatDay))
                .entrySet()
                .stream()
                .map(entry -> new TodoGroupDto(entry.getKey(), entry.getValue(), calcPresent(entry.getValue())))
                .toList());
    }

    private int calcPresent(List<TodoDto> value) {
        if (value.isEmpty())
            return 0;

        int size = 0;
        int done = 0;
        for (TodoDto todoDto : value) {
            int p = switch (todoDto.getType()) {
                case EASY -> 1;
                case MEDIUM -> 2;
                case HARD -> 3;
            };

            size += p;
            if (todoDto.isCompleted())
                done += p;
        }

        return (int) ((double) done / size * 100);
    }

    private static long thatDay(TodoDto todo) {
        long createdAt = todo.getCreatedAt();
        // 1000 * 60 * 60 * 24
        createdAt = createdAt - createdAt % (24 * 60 * 60 * 1000);
        return createdAt;
    }


    @Override
    public ApiResponse<TodoDto> add(TodoCreateDto todoCreateDto) {
        boolean exists = todoRepository.existsByTitleAndCreatedAtIsGreaterThan(todoCreateDto.title(), LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        if (exists)
            throw new MyConflictException("Todo already exists today");

        return ApiResponse.success(todoMapper.template(todoRepository.save(todoMapper.original(todoCreateDto))));
    }

    @Override
    public ApiResponse<TodoDto> edit(String id, TodoUpdateDto todoUpdateDto) {
        boolean exists = todoRepository.existsByTitleAndCreatedAtIsGreaterThanAndIdNot(todoUpdateDto.title(), LocalDateTime.of(LocalDate.now(), LocalTime.MIN), id);
        if (exists)
            throw new MyConflictException("Todo already exists today");

        return ApiResponse.success(todoMapper.template(todoMapper.update(todoUpdateDto, todoRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Todo not found by id")))));
    }

    @Override
    public ApiResponse<TodoDto> changeCompleted(String id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Todo not found by id"));
        todo.setCompleted(!todo.isCompleted());
        return ApiResponse.success(todoMapper.template(todoRepository.save(todo)));
    }

    @Override
    public void delete(String id) {
        if (!todoRepository.existsById(id))
            throw new MyNotFoundException("Todo not found by id");

        todoRepository.deleteById(id);
    }
}
