package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class InsufficientDrugAmountException extends MessageException {

    public InsufficientDrugAmountException() {
        super("Insufficient drugs in stock.");
    }
}
