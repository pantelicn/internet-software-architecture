package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyDto;

public class PharmacyMapper {
    public static PharmacyDto objectToDto(Pharmacy pharmacy) {
        return new PharmacyDto(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getAddress(),
                pharmacy.getRating(),
                pharmacy.getDescription()
        );
    }
}
