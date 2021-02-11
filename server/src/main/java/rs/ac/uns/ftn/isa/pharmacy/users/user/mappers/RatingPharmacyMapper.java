package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.PharmacyRating;
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

    public static PharmacyRating dtoToObject(RatingPharmacyDto dto) {
        var rating = new PharmacyRating();
        rating.setPharmacy(new Pharmacy());
        rating.getPharmacy().setId(dto.getId());
        rating.setRating(dto.getRating());
        return rating;
    }
}
