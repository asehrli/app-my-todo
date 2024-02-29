package org.example.appmytodo.payload;

import java.util.List;

public record ApiResponse<T>(String message,
                             boolean success,
                             T data,
                             List<ErrorDto> errors) {

    public static <S> ApiResponse<S> success(S data) {
        return new ApiResponse<>(null, true, data, null);
    }

    public static <E> ApiResponse<E> failed(String message) {
        return new ApiResponse<>(message, true, null, null);
    }

    public static <E> ApiResponse<E> failed(List<ErrorDto> errors) {
        return new ApiResponse<>(null, false, null, errors);
    }

}
