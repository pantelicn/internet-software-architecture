package rs.ac.uns.ftn.isa.pharmacy.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.repository.patients.PatientRepository;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getAppointedBy(long employeeId){
        return repository.getAppointedBy(employeeId);
    }

    public Patient get(long patientId){
        return repository.getOne(patientId);
    }

    public Patient update(Patient patient, long id) {
        if (repository.existsById(id)) {
            patient.setId(id);
            return repository.save(patient);
        }
        throw new EntityNotFoundException(Patient.class.getSimpleName(), id);
    }

    public boolean isAllergic(long patientId, long drugId) {
        return repository.getOne(patientId)
                .getAllergicTo()
                .stream()
                .anyMatch(d -> d.getId() == drugId);
    }

    public Patient findByPersonId(long id) {
        return repository.findByPersonId(id);
    }
}
