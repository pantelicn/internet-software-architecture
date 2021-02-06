package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class IncorrectPasswordException extends MessageException {
    public IncorrectPasswordException() {
        super("Password is incorrect");
    }
}
