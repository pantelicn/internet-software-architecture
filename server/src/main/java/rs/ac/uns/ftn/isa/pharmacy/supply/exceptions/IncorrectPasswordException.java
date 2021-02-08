package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class IncorrectPasswordException extends MessageException {
    public IncorrectPasswordException() {
        super("Password is incorrect");
    }
}
