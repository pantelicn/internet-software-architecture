package rs.ac.uns.ftn.isa.pharmacy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.AppointmentTimeException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientOccupiedException;

@ControllerAdvice
public class PersistenceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String entityNotFoundHandler(EntityNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String entityAlreadyExistsHandler(EntityAlreadyExistsException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AppointmentTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String appointmentTimeHandler(AppointmentTimeException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PatientOccupiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String patientOccupied(PatientOccupiedException e) {
        return e.getMessage();
    }
}
