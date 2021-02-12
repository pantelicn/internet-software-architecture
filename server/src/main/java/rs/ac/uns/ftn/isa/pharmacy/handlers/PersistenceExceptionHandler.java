package rs.ac.uns.ftn.isa.pharmacy.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.AllergyException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.QuantityException;
import rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions.*;

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

    @ResponseBody
    @ExceptionHandler(EmployeeOccupiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeOccupied(EmployeeOccupiedException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeShiftException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeShiftHandler(EmployeeShiftException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String patientAppointmentHandler(UserAccessException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(QuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String storedDrugQuantityHandler(QuantityException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String dateHandler(DateException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoUpcomingAppointmentsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noUpcomingAppointmentsHandler(NoUpcomingAppointmentsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AllergyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String allergyHandler(AllergyException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PenaltiesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String penaltyHandler(PenaltiesException e){
        return e.getMessage();
    }
}
