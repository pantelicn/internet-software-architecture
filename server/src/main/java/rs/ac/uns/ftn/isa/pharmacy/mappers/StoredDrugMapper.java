package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.dtos.DrugDto;

public class StoredDrugMapper {
    public static DrugDto objectToDto(StoredDrug drug) {
        return new DrugDto(
                drug.getId(),
                drug.getDrug().getName(),
                drug.getQuantity(),
                drug.getPrice(),
                drug.getDrug().getManufacturer()
        );
    }
}
