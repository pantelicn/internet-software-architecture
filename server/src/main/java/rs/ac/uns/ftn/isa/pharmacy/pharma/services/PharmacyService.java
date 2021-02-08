package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyAdminCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.PhAdminMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.PharmacyMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.repository.AdminRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

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
