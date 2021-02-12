package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugSimpleDto;

public class DrugSimpleMapper {

    public static DrugSimpleDto objectToDto(Drug drug) {
        return new DrugSimpleDto(drug.getId(), drug.getName());
    }
}
