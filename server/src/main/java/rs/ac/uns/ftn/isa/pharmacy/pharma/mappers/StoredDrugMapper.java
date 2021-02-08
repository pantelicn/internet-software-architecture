package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.StoredDrugDto;

public class StoredDrugMapper {
    public static StoredDrugDto objectToDto(StoredDrug storedDrug) {
        return new StoredDrugDto(
                storedDrug.getId(),
                storedDrug.getPrice(),
                storedDrug.getDrug(),
                storedDrug.getPharmacy()
        );
    }
}
