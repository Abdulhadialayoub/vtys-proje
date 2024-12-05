package com.rf.coin_analysis.exception_management;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // valid hataları
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> validationException(MethodArgumentNotValidException ex, HttpServletRequest http){
        Map<String,String> errors=new HashMap<>();
        for(FieldError fieldError : ex.getFieldErrors()){
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ApiResponse<Void> response=ApiResponse.<Void>builder().
                status(400).path(http.getRequestURI()).message("Doğrulama Hatası").errors(errors).dateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // 404 hataları
    @ExceptionHandler({NotFoundException.class, CoinException.class})
    public ResponseEntity<ApiResponse<Void>> notFoundException(RuntimeException ex,HttpServletRequest http){
        ApiResponse<Void> response=ApiResponse.<Void>builder().
        message(ex.getMessage()).status(404).path(http.getRequestURI()).dateTime(LocalDateTime.now()).
                build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    // bad request
    @ExceptionHandler(FavoriteCoinException.class)
    public ResponseEntity<ApiResponse<Void>> BadRequestException(RuntimeException ex,HttpServletRequest http){
        ApiResponse<Void> response=ApiResponse.<Void>builder().
                message(ex.getMessage()).status(400).path(http.getRequestURI()).dateTime(LocalDateTime.now()).
                build();
        return ResponseEntity.badRequest().body(response);
    }
    // 401 hataları
    @ExceptionHandler({TokenException.class, AuthException.class})
    public ResponseEntity<ApiResponse<Void>> UNAUTHORIZEDException(RuntimeException ex,HttpServletRequest http){
        ApiResponse<Void> response=ApiResponse.<Void>builder().
                message(ex.getMessage()).status(401).path(http.getRequestURI()).dateTime(LocalDateTime.now()).
                build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    // 403 hataları
    @ExceptionHandler(NoAuthorityException.class)
    public ResponseEntity<ApiResponse<Void>> forbiddenException(RuntimeException ex,HttpServletRequest http){
        ApiResponse<Void> response=ApiResponse.<Void>builder().
                message(ex.getMessage()).status(403).path(http.getRequestURI()).dateTime(LocalDateTime.now()).
                build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
