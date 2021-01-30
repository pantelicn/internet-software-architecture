package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientAppointmentException;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;

import javax.security.auth.message.AuthException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public Appointment findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Appointment.class.getSimpleName(), id));
    }

    @Transactional
    public List<Appointment> getFreeExaminations(long pharmacyId) {
        return repository.findFreeExaminations()
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId)
                .collect(Collectors.toList());
    }

    public Appointment createFreeExamination(Appointment appointment) {
        return repository.save(appointment);
    }

    public List<Appointment> getFreeExaminations(long pharmacyId, long dermatologistId) {
        return repository.findFreeExaminations()
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId &&
                        a.getShift().getEmployee().getId() == dermatologistId &&
                        a.getTerm().isInFuture())
                .collect(Collectors.toList());
    }

    public List<Appointment> getPatientAppointments(long patientId) {
        return repository.findAppointmentsByPatient(patientId);
    }

    public void cancelPatientAppointment(long patientId, long appointmentId) {
        Appointment appointment = findById(appointmentId);
        if (appointment.getPatient().getId() != patientId) {
            throw new PatientAppointmentException();
        }
        appointment.setPatient(null);
        repository.save(appointment);
    }
}
