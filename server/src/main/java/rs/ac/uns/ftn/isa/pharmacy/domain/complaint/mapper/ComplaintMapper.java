package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.UnansweredComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.patients.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;

import java.util.Optional;

@Component
public class ComplaintMapper {

    private final EmployeeRepository employeeRepository;
    private final PatientRepository patientRepository;
    private final PharmacyRepository pharmacyRepository;

    public ComplaintMapper(
            EmployeeRepository employeeRepository,
            PatientRepository patientRepository,
            PharmacyRepository pharmacyRepository
    ){
        this.employeeRepository = employeeRepository;
        this.patientRepository = patientRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    public Complaint dtoToObject(ComplaintCreationDto dto) throws InvalidForeignKeyException {
        Complaint complaint = new Complaint();

        complaint.setAnswered(false);
        complaint.setText(dto.getText());
        complaint.setType(dto.getType());

        Optional<Patient> optionalPatient = patientRepository.findById(dto.getPatientId());
        if (optionalPatient.isEmpty()) throw new InvalidForeignKeyException("Person");
        complaint.setAuthor(optionalPatient.get());

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
                complaint.getAuthor().getPerson().getFullName()
        );
    }
}
