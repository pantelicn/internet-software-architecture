package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingPharmacyDto;

public class RatingPharmacyMapper {
    public static RatingPharmacyDto objectToDto(Pharmacy pharmacy) {
        return new RatingPharmacyDto(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getRating(),
                pharmacy.getAddress()
        );
    }
}
