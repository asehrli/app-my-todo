package org.example.appmytodo.payload;

import java.util.List;

public record TodoGroupDto(long date, List<TodoDto> todos, int present) {
}
