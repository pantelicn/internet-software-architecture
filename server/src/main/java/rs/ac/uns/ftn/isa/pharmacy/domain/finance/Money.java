package rs.ac.uns.ftn.isa.pharmacy.domain.finance;


public class Money {
    private Currency currency;
    private float amount;

    public Money() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
