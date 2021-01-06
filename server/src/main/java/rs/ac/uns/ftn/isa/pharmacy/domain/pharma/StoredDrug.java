package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Price;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StoredDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quantity;

    private Price price;
    private Date validUntil;
    @OneToOne
    private Drug drug;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
