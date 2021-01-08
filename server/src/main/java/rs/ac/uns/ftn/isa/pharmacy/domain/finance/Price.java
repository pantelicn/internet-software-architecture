package rs.ac.uns.ftn.isa.pharmacy.domain.finance;

import java.io.Serializable;
import java.util.Date;

public class Price implements Serializable {
    private Money amount;
    private Date validUntil;

    public Money getAmount() {
        return amount;
    }
    public void setPrice(Money amount) {
        this.amount = amount;
    }

    public Date getValidUntil() {
        return validUntil;
    }
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
