package rs.ac.uns.ftn.isa.pharmacy.users.employee.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.EmployeeType;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Shift;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RegistrationService registrationService;

    public EmployeeService(EmployeeRepository employeeRepository, RegistrationService registrationService){
        this.employeeRepository = employeeRepository;
        this.registrationService = registrationService;
    }

    public long getEmployeeId(long personId){
        return this.employeeRepository.getEmployeeIdByPersonId(personId);
    }

    public List<Pharmacy> getMyPharmacies(long employeeId) {
        var pharmacies = new HashSet<Pharmacy>();
        for (Shift shift : employeeRepository.getOne(employeeId).getShifts()) {
            pharmacies.add(shift.getPharmacy());
        }
        return new ArrayList<>(pharmacies);
    }

    public Employee findByPersonId(long id) {
        return employeeRepository.findByPersonId(id);
    }

    public void createDermatologist(RegistrationDto dto) throws MessageException {
        Person person = registrationService.register(dto, Role.DERMATOLOGIST);
        Employee employee = new Employee();
        employee.setPerson(person);
        employee.setEmployeeType(EmployeeType.DERMATOLOGIST);
        employeeRepository.save(employee);
    }
}
