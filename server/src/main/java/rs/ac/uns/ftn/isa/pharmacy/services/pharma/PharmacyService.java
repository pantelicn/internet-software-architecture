package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.dto.PharmacyAdminCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.dto.PharmacyCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.mapper.PhAdminMapper;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.repository.AdminRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.mapper.PharmacyMapper;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.admin.Admin;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final AdminRepository adminRepository;
    private final RegistrationService registrationService;

    private final PharmacyMapper pharmacyMapper;
    private final PhAdminMapper phAdminMapper;

    public PharmacyService(
            PharmacyRepository pharmacyRepository,
            AdminRepository adminRepository,
            RegistrationService registrationService,
            PharmacyMapper pharmacyMapper,
            PhAdminMapper phAdminMapper
    ){
        this.pharmacyRepository = pharmacyRepository;
        this.adminRepository = adminRepository;
        this.registrationService = registrationService;
        this.pharmacyMapper = pharmacyMapper;
        this.phAdminMapper = phAdminMapper;
    }

    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public void create(PharmacyCreationDto dto) throws InvalidEntityException, InvalidForeignKeyException {
        Pharmacy pharmacy = pharmacyMapper.dtoToObject(dto);
        pharmacyRepository.save(pharmacy);
    }

    public void addAdmin(PharmacyAdminCreationDto dto) throws MessageException {
        Person createdPerson = registrationService.register(dto.getRegistrationDto(), Role.PH_ADMIN);

        Admin admin = phAdminMapper.dtoToObject(dto);
        if (admin.getPharmacy().getPharmacyAdmin() != null)
            throw new EntityExistsException("Pharmacy admin");
        admin.setPerson(createdPerson);

        Admin savedAdmin = adminRepository.saveAndFlush(admin);

        savedAdmin.getPharmacy().setPharmacyAdmin(savedAdmin);
        pharmacyRepository.save(savedAdmin.getPharmacy());
    }
}
