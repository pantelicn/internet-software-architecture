package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class MessageException extends Exception {
    protected String message;

    public MessageException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
