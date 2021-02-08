package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class InsufficientDrugAmountException extends MessageException {

    public InsufficientDrugAmountException() {
        super("Insufficient drugs in stock.");
    }
}
