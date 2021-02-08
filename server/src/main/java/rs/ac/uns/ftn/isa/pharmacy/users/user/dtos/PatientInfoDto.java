package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;

import java.util.List;

public class PatientInfoDto {
    private long id;
    private int penalties;
    private List<Drug> allergies;

    public PatientInfoDto(long id, int penalties, List<Drug> allergies) {
        this.id = id;
        this.penalties = penalties;
        this.allergies = allergies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public List<Drug> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Drug> allergies) {
        this.allergies = allergies;
    }
}
