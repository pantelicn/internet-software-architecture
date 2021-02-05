package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    private long id;
    private String name;
    private Drug.Type drugType;
    private Drug.IntakeType intakeType;
    @ElementCollection
    private List<String> ingredients;
    private String manufacturer;
    private boolean requiresPrescription;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Drug> alternatives;
    private String additionalNotes;

    public enum IntakeType {
        CAPSULE, POWDER, OINTMENT, TABLET, PASTE, GEL, SYRUP, SOLUTION
    }

    public enum Type {
        NERVE, CARDIO, RESPIRATORY, BLOOD, ANTIINFECTANT
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getDrugType() {
        return drugType;
    }

    public void setDrugType(Type drugType) {
        this.drugType = drugType;
    }

    public IntakeType getIntakeType() {
        return intakeType;
    }

    public void setIntakeType(IntakeType intakeType) {
        this.intakeType = intakeType;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public List<Drug> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Drug> alternatives) {
        this.alternatives = alternatives;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
