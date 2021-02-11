package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugSearchDto;

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
