package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Price;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.QuantityException;

import javax.persistence.*;

@Entity
@Table(name = "stored_drugs")
public class StoredDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long quantity;
    @Embedded
    private Price price;
    @OneToOne
    private Drug drug;
    @ManyToOne
    Pharmacy pharmacy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        if (quantity < 0) {
            throw new QuantityException("There is not enough of the stored drug.");
        }
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
