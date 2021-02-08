package rs.ac.uns.ftn.isa.pharmacy.complaint.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.UnansweredComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.users.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;

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


    public UnansweredComplaintDto objectToDto(Complaint complaint) {
        return new UnansweredComplaintDto(
                complaint.getId(),
                complaint.getText(),
                complaint.getType(),
                complaint.getAuthor().getFirstName() + " " + complaint.getAuthor().getLastName()
        );
    }
}
