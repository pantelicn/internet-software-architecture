package rs.ac.uns.ftn.isa.pharmacy.domain.finance;


import java.io.Serializable;

public class Money implements Serializable {
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
