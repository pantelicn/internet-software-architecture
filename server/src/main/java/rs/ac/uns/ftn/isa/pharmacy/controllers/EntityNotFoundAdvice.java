package rs.ac.uns.ftn.isa.pharmacy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.exceptions.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String entityNotFoundHandler(EntityNotFoundException e) {
        return e.getMessage();
    }
}
