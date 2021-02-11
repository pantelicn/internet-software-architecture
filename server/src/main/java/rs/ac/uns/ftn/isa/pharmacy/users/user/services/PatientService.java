package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final RegistrationService registrationService;

    public PatientService(PatientRepository patientRepository, RegistrationService registrationService) {
        this.patientRepository = patientRepository;
        this.registrationService = registrationService;
    }

    public List<Patient> getAppointedBy(long employeeId){
        return patientRepository.getAppointedBy(employeeId);
    }

    public Patient get(long patientId){
        return patientRepository.getOne(patientId);
    }

    public Patient update(Patient patient, long id) {
        if (patientRepository.existsById(id)) {
            patient.setId(id);
            return patientRepository.save(patient);
        }
        throw new EntityNotFoundException(Patient.class.getSimpleName(), id);
    }

    public boolean isAllergic(long patientId, long drugId) {
        return patientRepository.getOne(patientId)
                .getAllergicTo()
                .stream()
                .anyMatch(d -> d.getId() == drugId);
    }

    public Patient findByPersonId(long id) {
        return patientRepository.findByPersonId(id);
    }

    public void updateAllergies(List<Drug> drugs, long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException(Patient.class.getSimpleName(), patientId));
        patient.setAllergicTo(drugs);
        patientRepository.save(patient);
    }

    @Transactional
    public void eraseAllPenalties() {
        patientRepository.eraseAllPenalties();
    }

    public void register(RegistrationDto dto)
            throws EntityExistsException, rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException,
            InvalidEntityException
    {
        Person person = registrationService.registerPatient(dto);
        Patient patient = new Patient();
        patient.setPenalties(0);
        patient.setPerson(person);
        patientRepository.save(patient);
    }
}
