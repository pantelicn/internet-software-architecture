package rs.ac.uns.ftn.isa.pharmacy.domain.supply.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.Supplier;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final RegistrationService registrationService;

    public SupplierService(SupplierRepository supplierRepository, RegistrationService registrationService) {
        this.supplierRepository = supplierRepository;
        this.registrationService = registrationService;
    }

    public void create(RegistrationDto dto) throws EntityExistsException, EntityNotFoundException, InvalidEntityException {
        Person person = registrationService.register(dto, Role.SUPPLIER);
        Supplier supplier = new Supplier();
        supplier.setPersonId(person.getId());
        supplierRepository.save(supplier);
    }
}
