package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.AppointmentType;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Shift;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Term;
import rs.ac.uns.ftn.isa.pharmacy.dtos.CreatedAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PharmacistCounselingDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PredefinedAppointmentReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.AppointmentTimeException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EmployeeOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EmployeeShiftException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.repository.patients.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;
import rs.ac.uns.ftn.isa.pharmacy.services.notifiers.EmailService;

import javax.persistence.PersistenceException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SchedulingService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public SchedulingService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                             EmployeeRepository employeeRepository, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }
    @Transactional
    public void schedulePredefinedAppointment(PredefinedAppointmentReservationDto appointmentReservation) throws PatientOccupiedException{
        var appointment = appointmentRepository.getOne(appointmentReservation.getAppointmentId());
        var patient = patientRepository.getOne(appointmentReservation.getPatientId());

        if(!patient.canSchedule(appointment))
            throw new PatientOccupiedException();
        else if (!appointment.getTerm().isInFuture())
            throw new AppointmentTimeException();

        appointment.setPatient(patient);
        appointment = appointmentRepository.save(appointment);
        emailService.sendExaminationScheduledMessage(appointment);
    }
    // TODO: Refactor!
    @Transactional
    public void scheduleNewAppointment(CreatedAppointmentDto createdAppointmentDto, AppointmentType type) throws PersistenceException {
        var term = createdAppointmentDto.getTerm();

        var employee = employeeRepository.getOne(createdAppointmentDto.getEmployeeId());
        var patient = patientRepository.getOne(createdAppointmentDto.getPatientId());
        var appointment = new Appointment(term, type,null,null);

        if(employee.isOccupied(term))
            throw new EmployeeOccupiedException();
        if(!patient.canSchedule(appointment))
            throw new PatientOccupiedException();
        if(!employee.hasShiftAtPharmacy(term,createdAppointmentDto.getPharmacyId()))
            throw new EmployeeShiftException();

        var shift = employee.getAdequateShift(term);
        appointment.setPatient(patient);
        appointment.setShift(shift);
        appointment = appointmentRepository.save(appointment);
        emailService.sendExaminationScheduledMessage(appointment);
    }

    public List<Shift> findAvailableShiftsForCounseling(LocalDateTime dateTime) {
        var term = new Term(dateTime, Duration.ofMinutes(30));
        var shifts = new ArrayList<Shift>();
        for(var employee: employeeRepository.getPharmacist()) {
            if(!employee.isOccupied(term)) {
                var shift = getShift(employee, term);
                if (shift != null)
                    shifts.add(shift);
            }
        }
        return shifts;
    }

    private Shift getShift(Employee employee, Term term) {
        for (var shift: employee.getShifts()) {
            if (shift.asTerm().contains(term))
                return shift;
        }
        return null;
    }
}
