package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.City;
import rs.ac.uns.ftn.isa.pharmacy.locale.repository.CityRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyDetailsDto;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;

import java.util.Optional;

@Component
public class PharmacyMapper {

    private final CityRepository cityRepository;

    public PharmacyMapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public static PharmacyDetailsDto objectToDto(Pharmacy pharmacy) {
        return new PharmacyDetailsDto(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getAddress(),
                pharmacy.getRating(),
                pharmacy.getDescription()
        );
    }

    public Pharmacy dtoToObject(PharmacyCreationDto dto) throws InvalidEntityException, InvalidForeignKeyException {
        Pharmacy pharmacy = new Pharmacy();
        validate(dto);

        Optional<City> optionalCity = cityRepository.findById(dto.getAddress().getCity().getId());
        if (optionalCity.isEmpty())
            throw new InvalidForeignKeyException("City");
        dto.getAddress().setCity(optionalCity.get());

        pharmacy.setName(dto.getName());
        pharmacy.setDescription(dto.getDescription());
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setCounselingPrice(dto.getCounselingPrice());

        return pharmacy;
    }

    private void validate(PharmacyCreationDto dto) throws InvalidEntityException {
        if (dto.getName() == null || dto.getName().isEmpty()) throw new InvalidEntityException("Name");
        dto.getAddress().validateSelf();
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) throw new InvalidEntityException("Description");
        if (dto.getCounselingPrice() == null || dto.getCounselingPrice().getAmount() < 0)
            throw new InvalidEntityException("Counseling price");
    }
}
