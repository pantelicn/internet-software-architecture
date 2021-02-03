package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class PasswordTooShortException extends MessageException {
    public PasswordTooShortException() {
        this.message = "Password too short";
    }
}
