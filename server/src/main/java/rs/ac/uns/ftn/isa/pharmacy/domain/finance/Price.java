package rs.ac.uns.ftn.isa.pharmacy.domain.finance;

import java.io.Serializable;
import java.util.Date;

public class Price implements Serializable {
    private Money price;
    private Date validUntil;

    public Money getPrice() {
        return price;
    }
    public void setPrice(Money price) {
        this.price = price;
    }

    public Date getValidUntil() {
        return validUntil;
    }
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
