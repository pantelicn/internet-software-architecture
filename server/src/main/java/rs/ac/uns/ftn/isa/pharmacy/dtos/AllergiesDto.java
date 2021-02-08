package rs.ac.uns.ftn.isa.pharmacy.dtos;

import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;

import java.util.ArrayList;
import java.util.List;

public class AllergiesDto {
    private List<Drug> allergicTo;

    public AllergiesDto(List<Drug> allergicTo) {
        this.allergicTo = allergicTo;
    }

    public AllergiesDto() {
        this.allergicTo = new ArrayList<Drug>();
    }

    public List<Drug> getAllergicTo() {
        return allergicTo;
    }

    public void setAllergicTo(List<Drug> allergicTo) {
        this.allergicTo = allergicTo;
    }
}
