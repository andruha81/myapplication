package by.transport.myapp.exception;

import by.transport.myapp.controller.RouteController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LogManager.getLogger(RouteController.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(
            EntityNotFoundException exception,
            Model model) {
        logger.error("Failed to find the requested element", exception);

        model.addAttribute("error", exception.getMessage());
        return "error/error404";
    }
}
