package rs.ac.uns.ftn.isa.pharmacy.users.user.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DrugRating extends Rating {
    @ManyToOne
    @JoinColumn(name="drug_id")
    private Drug drug;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
