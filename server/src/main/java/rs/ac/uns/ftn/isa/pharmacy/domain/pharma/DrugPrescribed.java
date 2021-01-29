package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import javax.persistence.*;

@Entity
@Table(name = "drug_prescribed")
public class DrugPrescribed {
    @Id
    private long id;
    @OneToOne
    private Prescription prescription;
    @OneToOne
    private Drug drug;
    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
