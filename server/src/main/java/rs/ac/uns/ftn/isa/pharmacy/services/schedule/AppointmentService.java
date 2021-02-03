package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.AppointmentTimeException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.UserAccessException;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Appointment.class.getSimpleName(), id));
    }

    @Transactional
    public List<Appointment> getFreeExaminations(long pharmacyId) {
        return appointmentRepository.findFreeExaminations()
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId)
                .collect(Collectors.toList());
    }

    public Appointment createFreeExamination(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getFreeExaminations(long pharmacyId, long dermatologistId) {
        return appointmentRepository.findFreeExaminations()
                .stream()
                .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId &&
                        a.getShift().getEmployee().getId() == dermatologistId &&
                        a.getTerm().isInFuture())
                .collect(Collectors.toList());
    }

    public List<Appointment> getPatientAppointments(long patientId) {
        return appointmentRepository.findAppointmentsByPatient(patientId);
    }

    public void cancelPatientAppointment(long patientId, long appointmentId) {
        Appointment appointment = findById(appointmentId);
        if (appointment.getPatient().getId() != patientId) {
            throw new UserAccessException();
        } else if (!appointment.getTerm().isInFuture()) {
            throw new AppointmentTimeException();
        }
        appointment.setPatient(null);
        appointmentRepository.save(appointment);
    }
    public List<Appointment> getPastExaminations(long patientId){
        return appointmentRepository.findExaminationsByPatient(patientId)
                .stream()
                .filter(e->e.getTerm().isInPast())
                .collect(Collectors.toList());
    }
    public List<Appointment> getPastCounselingsInPharmacy(long patientId,long pharmacistId){
        var pharmacyId = getPharmacyId(pharmacistId);
        return appointmentRepository
                .findCounselingsByPatient(patientId)
                .stream()
                .filter(a->a.getShift().getPharmacy().getId() == pharmacyId)
                .collect(Collectors.toList());
    }

    private long getPharmacyId(long pharmacistId) {
        return employeeRepository.getOne(pharmacistId)
                .getShifts()
                .stream()
                .findFirst()
                .get()
                .getPharmacy()
                .getId();
    }
}
