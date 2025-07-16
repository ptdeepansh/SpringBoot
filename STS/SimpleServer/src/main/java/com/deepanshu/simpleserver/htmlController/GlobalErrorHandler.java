package com.deepanshu.simpleserver.htmlController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404() {
        return "404"; // returns the Thymeleaf template
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Model model) {
    	model.addAttribute("Value", "501 - Critical Issue please contact Dev Immediately");
        return "404";
    }
}