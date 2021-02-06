package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class PasswordTooShortException extends MessageException {
    public PasswordTooShortException() {
        super("Password too short");
    }
}
