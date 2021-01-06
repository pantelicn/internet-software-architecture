package rs.ac.uns.ftn.isa.pharmacy.domain.finance;


public class Money {
    private float amount;

    public String getCurrency() {
        return "RSD";
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
}
