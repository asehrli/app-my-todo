package org.example.appmytodo.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.appmytodo.enums.TodoType;

public record TodoCreateDto(@NotBlank(message = "Title is required") String title,
                            String description,
                            @NotNull TodoType type,
                            Boolean required) {
}
