package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import java.time.LocalDateTime;
import java.util.List;

public class EPrescription {

    private long  id;
    private String firstName;
    private String lastName;
    private LocalDateTime dispensingDate;
    private List<EPrescriptionDrug> prescribedDrugs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDispensingDate() {
        return dispensingDate;
    }

    public void setDispensingDate(LocalDateTime dispensingDate) {
        this.dispensingDate = dispensingDate;
    }

    public List<EPrescriptionDrug> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(List<EPrescriptionDrug> prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }
}
