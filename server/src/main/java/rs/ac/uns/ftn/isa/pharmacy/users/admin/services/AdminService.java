package rs.ac.uns.ftn.isa.pharmacy.users.admin.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.AdminType;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.repository.AdminRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final RegistrationService registrationService;

    public AdminService(AdminRepository adminRepository, RegistrationService registrationService) {
        this.adminRepository = adminRepository;
        this.registrationService = registrationService;
    }

    public void addSystemAdmin(RegistrationDto dto)
            throws EntityExistsException, EntityNotFoundException, InvalidEntityException
    {
        Person person = registrationService.register(dto, Role.SYS_ADMIN);
        Admin admin = new Admin();
        admin.setPerson(person);
        admin.setAdminType(AdminType.SYSTEM_ADMIN);
        adminRepository.save(admin);
    }
}
