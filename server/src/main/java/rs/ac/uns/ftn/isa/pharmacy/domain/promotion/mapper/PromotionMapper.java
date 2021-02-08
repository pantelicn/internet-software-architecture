package rs.ac.uns.ftn.isa.pharmacy.domain.promotion.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.promotion.dto.PromotionCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.promotion.model.Promotion;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;

import java.util.Optional;

@Component
public class PromotionMapper {

    private final PharmacyRepository pharmacyRepository;

    public PromotionMapper(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Promotion dtoToObject(PromotionCreationDto dto) throws InvalidEntityException {
        Promotion promotion = new Promotion();

        promotion.setExpirationDate(dto.getExpirationDate());
        promotion.setText(dto.getText());

        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(dto.getPharmacyId());
        if (optionalPharmacy.isEmpty()) throw new InvalidEntityException("Pharmacy");

        promotion.setPharmacy(optionalPharmacy.get());

        return promotion;
    }
}
