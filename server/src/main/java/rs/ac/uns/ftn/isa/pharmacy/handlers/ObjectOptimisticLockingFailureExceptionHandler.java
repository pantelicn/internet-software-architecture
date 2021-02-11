package rs.ac.uns.ftn.isa.pharmacy.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ObjectOptimisticLockingFailureExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String optimisticLockHandler() {
        return "State has changed. Please try again.";
    }
}
