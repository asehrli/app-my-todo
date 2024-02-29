package org.example.appmytodo.document;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.appmytodo.enums.TodoType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Todo implements Comparable<Todo>{
    @Id
    String id;
    String title;
    String description;
    TodoType type;
    boolean completed;
    boolean required;
    @CreatedDate
    long createdAt = System.currentTimeMillis();

    @Override
    public int compareTo(Todo o) {
        return o.type.compareTo(this.type);
    }
}
