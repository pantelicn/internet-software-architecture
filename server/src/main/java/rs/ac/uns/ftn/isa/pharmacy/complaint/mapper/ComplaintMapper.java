package rs.ac.uns.ftn.isa.pharmacy.complaint.mapper;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.UnansweredComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PatientRepository;

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

    @Transactional
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
