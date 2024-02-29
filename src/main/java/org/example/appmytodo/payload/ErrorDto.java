package org.example.appmytodo.payload;

public record ErrorDto(String field,
                       String message,
                       int status) {
}
