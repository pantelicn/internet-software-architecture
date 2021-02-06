package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class LateDeadlineException extends MessageException {

    public LateDeadlineException() {
        super("Offered deadline is in the past.");
    }
}
