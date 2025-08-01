package com.deepanshu.simpleserver.htmlController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalErrorHandler {

    // Handles 404 errors (Page Not Found)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(Model model) {
        model.addAttribute("errorTitle", "404 - Page Not Found");
        model.addAttribute("errorMessage", "The page you're looking for doesn't exist.");
        return "404";
    }

    // Handles all other exceptions (e.g. 500 errors)
    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Model model, Exception ex) {
        model.addAttribute("errorTitle", "500 - Internal Server Error");
        model.addAttribute("errorMessage", "An unexpected error occurred. Please contact support.");
        model.addAttribute("exception", ex); // optional: for debugging
        return "404"; // still using 404.html, but with different content
    }
}
