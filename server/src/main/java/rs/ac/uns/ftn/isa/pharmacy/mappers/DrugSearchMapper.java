package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.dtos.DrugSearchDto;

public class DrugSearchMapper {
    public static DrugSearchDto objectToDto(StoredDrug drug) {
        return new DrugSearchDto(
                drug.getId(),
                drug.getDrug().getId(),
                drug.getDrug().getName(),
                drug.getQuantity(),
                drug.getPrice(),
                drug.getDrug().getManufacturer(),
                drug.getPharmacy().getId(),
                drug.getPharmacy().getName()
        );
    }
}
