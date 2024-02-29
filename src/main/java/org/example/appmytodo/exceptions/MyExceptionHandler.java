package org.example.appmytodo.exceptions;

import org.example.appmytodo.payload.ApiResponse;
import org.example.appmytodo.payload.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    public ResponseEntity<ApiResponse<?>> myExceptionHandler(MyException e) {
        return ResponseEntity.status(e.getStatus()).body(ApiResponse.failed(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> notValidHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ApiResponse.failed(e.getFieldErrors().stream().map(fe -> new ErrorDto(fe.getField(), fe.getDefaultMessage(), 400)).toList()));
    }
}
