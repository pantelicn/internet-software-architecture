package rs.ac.uns.ftn.isa.pharmacy.users.user.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PharmacyRating extends Rating {
    @ManyToOne
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
