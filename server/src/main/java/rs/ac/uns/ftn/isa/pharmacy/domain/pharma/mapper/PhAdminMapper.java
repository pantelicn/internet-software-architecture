package rs.ac.uns.ftn.isa.pharmacy.domain.pharma.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.dto.PharmacyAdminCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.admin.Admin;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.admin.AdminType;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;

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
