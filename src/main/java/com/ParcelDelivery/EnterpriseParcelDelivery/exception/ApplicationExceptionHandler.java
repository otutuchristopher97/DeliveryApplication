package com.ParcelDelivery.EnterpriseParcelDelivery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, Object> handBadRequestException(BadRequestException ex){
        Map<String, Object> errorMap=new HashMap<>();
        errorMap.put("statusCode", 400);
        errorMap.put("message", ex.getMessage());
        return errorMap;

    }

}
