package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.QuantityException;

import javax.persistence.*;

@Entity
@Table(name = "drug_reservations")
public class DrugReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private StoredDrug storedDrug;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public StoredDrug getStoredDrug() {
        return storedDrug;
    }

    public void setStoredDrug(StoredDrug storedDrug) {
        this.storedDrug = storedDrug;
    }

    public int getQuantity() {
        if (quantity < 1) {
            throw new QuantityException();
        }
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
