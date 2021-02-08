package rs.ac.uns.ftn.isa.pharmacy.finance;

import javax.persistence.Embeddable;
import java.util.Date;
@Embeddable
public class Price {
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
