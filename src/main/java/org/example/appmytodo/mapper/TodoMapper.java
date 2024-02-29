package org.example.appmytodo.mapper;

import org.example.appmytodo.document.Todo;
import org.example.appmytodo.payload.TodoCreateDto;
import org.example.appmytodo.payload.TodoDto;
import org.example.appmytodo.payload.TodoUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto template(Todo todo);

    Todo original(TodoCreateDto todoCreateDto);

    List<TodoDto> templates(List<Todo> todos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Todo update(TodoUpdateDto todoUpdateDto, @MappingTarget Todo todo);
}
