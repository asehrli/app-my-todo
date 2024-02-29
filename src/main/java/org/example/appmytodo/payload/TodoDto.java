package org.example.appmytodo.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.appmytodo.enums.TodoType;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TodoDto {
    String id;
    String title;
    String description;
    TodoType type;
    boolean completed;
    boolean required;
    long createdAt;
}
