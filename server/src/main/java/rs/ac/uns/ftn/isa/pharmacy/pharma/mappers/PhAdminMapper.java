package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyAdminCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.AdminType;
import rs.ac.uns.ftn.isa.pharmacy.users.person.repository.PersonRepository;


import java.util.Optional;

@Component
public class PhAdminMapper {

    private final PersonRepository personRepository;
    private final PharmacyRepository pharmacyRepository;


    public PhAdminMapper(PersonRepository personRepository, PharmacyRepository pharmacyRepository) {
        this.personRepository = personRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    public Admin dtoToObject(PharmacyAdminCreationDto dto) throws InvalidForeignKeyException {
        Admin admin = new Admin();

        admin.setAdminType(AdminType.PHARMACY_ADMIN);
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(dto.getPharmacyId());
        if (optionalPharmacy.isEmpty()) throw new InvalidForeignKeyException("Pharmacy");
        admin.setPharmacy(optionalPharmacy.get());

        return admin;
    }
}
