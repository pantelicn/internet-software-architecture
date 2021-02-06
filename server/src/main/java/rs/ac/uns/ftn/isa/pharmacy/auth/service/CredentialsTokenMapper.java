package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.AuthToken;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.services.employee.EmployeeService;
import rs.ac.uns.ftn.isa.pharmacy.services.patients.PatientService;

@Service
public class CredentialsTokenMapper {
    private PatientService patientService;
    private EmployeeService employeeService;

    public CredentialsTokenMapper(PatientService patientService, EmployeeService employeeService) {
        this.patientService = patientService;
        this.employeeService = employeeService;
    }

    public AuthToken createAuthToken(Credentials credentials) {
        AuthToken token = new AuthToken();
        token.setEmail(credentials.getEmail());
        token.setUserId(credentials.getPerson().getId());
        token.setRole(credentials.getRole());
        AttachRoleId(token, credentials);
        return token;
    }

    private void AttachRoleId(AuthToken token, Credentials credentials) {
        if (credentials.getRole().equals(Role.PATIENT)) {
            Patient patient = patientService.findByPersonId(credentials.getPerson().getId());
            token.setRoleId(patient.getId());
        }
        else if (credentials.getRole().equals(Role.DERMATOLOGIST)
                || credentials.getRole().equals(Role.PHARMACIST)){
            Employee employee = employeeService.findByPersonId(credentials.getPerson().getId());
            token.setRoleId(employee.getId());
        }
    }

}
