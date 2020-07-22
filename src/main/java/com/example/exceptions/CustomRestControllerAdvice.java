package com.example.exceptions;


import com.example.model.responses.ResponseObject;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    protected ResponseObject<String> handleConflict(Exception e) {
        ResponseObject<String> response = new ResponseObject<>();
        response.setError(e.getMessage());
        return response;
    }
}