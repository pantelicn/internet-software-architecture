package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.dtos.StoredDrugDto;

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
