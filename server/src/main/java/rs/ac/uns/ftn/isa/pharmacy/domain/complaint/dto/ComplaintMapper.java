package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;

import java.util.Optional;

@Component
public class ComplaintMapper {

    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final PharmacyRepository pharmacyRepository;

    public ComplaintMapper(
            EmployeeRepository employeeRepository,
            PersonRepository personRepository,
            PharmacyRepository pharmacyRepository
    ){
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    public Complaint dtoToObject(ComplaintCreationDto dto) throws InvalidForeignKeyException {
        Complaint complaint = new Complaint();

        complaint.setAnswered(false);
        complaint.setText(dto.getText());
        complaint.setType(dto.getType());

        Optional<Person> optionalPerson = personRepository.findById(dto.getPersonId());
        if (optionalPerson.isEmpty()) throw new InvalidForeignKeyException("Person");
        complaint.setAuthor(optionalPerson.get());

        if (complaint.getType() == Complaint.Type.EMPLOYEE_COMPLAINT) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(dto.getEmployeeId());
            if (optionalEmployee.isEmpty()) throw new InvalidForeignKeyException("Employee");
            complaint.setEmployee(optionalEmployee.get());
        }
        else {
            Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(dto.getPharmacyId());
            if (optionalPharmacy.isEmpty()) throw new InvalidForeignKeyException("Pharmacy");
            complaint.setPharmacy(optionalPharmacy.get());
        }

        return complaint;
    }
}
