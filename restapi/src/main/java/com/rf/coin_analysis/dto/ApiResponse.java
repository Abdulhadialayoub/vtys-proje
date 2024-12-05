package com.rf.coin_analysis.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    private int status;
    private String path;
    private String message;
    private LocalDateTime dateTime=LocalDateTime.now();
    private Map<String,String> errors=new HashMap<>();
    public T data;

    public static <T> ApiResponse<T> ok(String message) {
        return ApiResponse.<T>builder()
                .dateTime(LocalDateTime.now())
                .status(200)
                .message(message)
                .build();
    }


    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder()
                .dateTime(LocalDateTime.now())
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

}
