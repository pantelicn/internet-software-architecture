package rs.ac.uns.ftn.isa.pharmacy.finance;

import javax.persistence.Embeddable;

@Embeddable
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
