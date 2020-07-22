package com.example.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomMVCControllerAdvice {

    @ExceptionHandler(value = CustomException.class )
    protected String mvcError(Model model, CustomException e) {
        model.addAttribute("message", e.getMessage());
        return "admin/index";
    }
}
