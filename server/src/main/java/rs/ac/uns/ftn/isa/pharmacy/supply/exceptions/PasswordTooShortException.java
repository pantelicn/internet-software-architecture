package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class PasswordTooShortException extends MessageException {
    public PasswordTooShortException() {
        super("Password too short");
    }
}
