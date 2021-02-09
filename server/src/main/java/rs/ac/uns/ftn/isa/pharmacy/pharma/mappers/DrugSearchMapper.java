package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugSearchDto;

public class DrugSearchMapper {
    public static DrugSearchDto objectToDto(StoredDrug storedDrug) {
        return new DrugSearchDto(
                storedDrug.getId(),
                storedDrug.getDrug().getId(),
                storedDrug.getDrug().getName(),
                storedDrug.getQuantity(),
                storedDrug.getPrice(),
                storedDrug.getDrug().getManufacturer(),
                storedDrug.getPharmacy().getId(),
                storedDrug.getPharmacy().getName(),
                storedDrug.getDrug().getAdditionalNotes(),
                storedDrug.getDrug().getIntakeType(),
                storedDrug.getDrug().getDrugType(),
                storedDrug.getDrug().getRating()
        );
    }
}
