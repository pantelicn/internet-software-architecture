package rs.ac.uns.ftn.isa.pharmacy.promotion.model;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    private LocalDateTime expirationDate;
    @ManyToOne
    private Pharmacy pharmacy;

    public void validate() throws InvalidEntityException {
        if (text == null || expirationDate == null || pharmacy == null)
            throw new InvalidEntityException("Promotion");
        if (text.length() < 10) throw new InvalidEntityException("Promotion text");
        if (expirationDate.isBefore(LocalDateTime.now())) throw new InvalidEntityException("Expiration date");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }


}
