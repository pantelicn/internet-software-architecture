package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import javax.persistence.*;

@Entity
@Table(name = "drugs_prescribed")
public class DrugPrescribed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    @OneToOne
    private Drug drug;
    private int therapyDuration;

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

    public int getTherapyDuration() {
        return therapyDuration;
    }

    public void setTherapyDuration(int therapyDuration) {
        this.therapyDuration = therapyDuration;
    }
}
