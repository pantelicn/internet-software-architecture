package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.AuthToken;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.services.EmployeeService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.PatientService;

@Service
public class CredentialsTokenMapper {
    private final PatientService patientService;
    private final EmployeeService employeeService;
    private final PharmacyRepository pharmacyRepository;

    public CredentialsTokenMapper(
            PatientService patientService,
            EmployeeService employeeService,
            PharmacyRepository pharmacyRepository
    ){
        this.patientService = patientService;
        this.employeeService = employeeService;
        this.pharmacyRepository = pharmacyRepository;
    }

    public AuthToken createAuthToken(Credentials credentials) {
        AuthToken token = new AuthToken();
        token.setEmail(credentials.getEmail());
        token.setPersonId(credentials.getPerson().getId());
        token.setRole(credentials.getRole());
        AttachRoleId(token, credentials);
        return token;
    }

    private void AttachRoleId(AuthToken token, Credentials credentials) {
        switch (credentials.getRole()) {
            case Role.PATIENT:
                Patient patient = patientService.findByPersonId(credentials.getPerson().getId());
                System.out.println("Searching for person="+credentials.getPerson().getId());
                System.out.println("Patient id=" + patient.getId());
                token.setRoleId(patient.getId());
                break;
            case Role.DERMATOLOGIST:
            case Role.PHARMACIST:
                Employee employee = employeeService.findByPersonId(credentials.getPerson().getId());
                token.setRoleId(employee.getId());
                break;
            case Role.PH_ADMIN:
                Pharmacy pharmacy = pharmacyRepository.getByAdmin(credentials.getPerson().getId());
                token.setRoleId(pharmacy.getId());
                break;
        }
    }

}
