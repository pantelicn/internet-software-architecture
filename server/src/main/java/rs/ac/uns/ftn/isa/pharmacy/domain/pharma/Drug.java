package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String drugType;
    private String intakeType;
    @ElementCollection
    private List<String> ingredients;
    private boolean requiresPrescription;
    private String additionalNotes;
    @ManyToMany
    private List<Drug> alternatives;

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

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getIntakeType() {
        return intakeType;
    }

    public void setIntakeType(String intakeType) {
        this.intakeType = intakeType;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public List<Drug> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Drug> alternatives) {
        this.alternatives = alternatives;
    }


}
