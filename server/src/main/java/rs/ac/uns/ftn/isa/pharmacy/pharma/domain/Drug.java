package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.DrugRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Rating;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drugs")
@SequenceGenerator(name = "drug_seq", initialValue = 100)
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drug_seq")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="drug")
    @JsonIgnore
    private List<DrugRating> ratings;

    public void validate() throws InvalidEntityException {
        if (name == null || name.isEmpty()) throw new InvalidEntityException("Name");
        if (drugType == null) throw new InvalidEntityException("Drug type");
        if (intakeType == null) throw new InvalidEntityException("Intake type");
        if (ingredients == null || ingredients.isEmpty()) throw new InvalidEntityException("Ingredients");
        if (manufacturer == null || manufacturer.isEmpty()) throw new InvalidEntityException("Manufacturer");
        if (additionalNotes == null) throw new InvalidEntityException("Additional notes");
    }

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

    public double getRating() {
        double totalRating = 0;
        int size = this.ratings.size();
        if (size == 0) return 0;
        for (var rating: this.ratings) {
            totalRating += rating.getRating();
        }
        return totalRating / size;
    }

    public List<DrugRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<DrugRating> ratings) {
        this.ratings = ratings;
    }
}
