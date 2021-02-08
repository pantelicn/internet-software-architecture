package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;

import java.util.List;

public class DrugCreationDto {

    private Drug drug;
    private List<Integer> alternativeDrugIds;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public List<Integer> getAlternativeDrugIds() {
        return alternativeDrugIds;
    }

    public void setAlternativeDrugIds(List<Integer> alternativeDrugIds) {
        this.alternativeDrugIds = alternativeDrugIds;
    }
}
