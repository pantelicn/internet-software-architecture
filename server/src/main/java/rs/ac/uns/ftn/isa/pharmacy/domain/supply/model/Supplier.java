package rs.ac.uns.ftn.isa.pharmacy.domain.supply.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private List<SupplierStock> drugsInStock;
    @OneToMany
    private List<Offer> offers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SupplierStock> getDrugsInStock() {
        return drugsInStock;
    }

    public void setDrugsInStock(List<SupplierStock> drugsInStock) {
        this.drugsInStock = drugsInStock;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
