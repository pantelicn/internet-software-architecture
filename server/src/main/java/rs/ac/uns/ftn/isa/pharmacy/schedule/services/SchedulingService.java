package rs.ac.uns.ftn.isa.pharmacy.schedule.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.AppointmentType;
import rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Shift;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Term;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.CreatedAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.PredefinedAppointmentReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.schedule.repository.AppointmentRepository;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;

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
        var appointment = appointmentRepository.findById(appointmentReservation.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException(Appointment.class.getSimpleName(), appointmentReservation.getAppointmentId()));
        var patient = patientRepository.getOne(appointmentReservation.getPatientId());

        if(!patient.canSchedule(appointment))
            throw new PatientOccupiedException();
        else if (!appointment.getTerm().isInFuture())
            throw new AppointmentTimeException();
        else if (appointment.getPatient() != null) {
            throw new EmployeeOccupiedException();
        }

        appointment.setPatient(patient);
        appointment = appointmentRepository.save(appointment);
        emailService.sendExaminationScheduledMessage(appointment);
    }

    @Transactional
    public void scheduleNewAppointmentPatient(CreatedAppointmentDto createdAppointmentDto, AppointmentType type) {
        checkPatientBanned(createdAppointmentDto.getPatientId());
        scheduleNewAppointment(createdAppointmentDto, type);
    }

    @Transactional
    public void schedulePredefinedAppointmentPatient(PredefinedAppointmentReservationDto appointmentReservation) {
        checkPatientBanned(appointmentReservation.getPatientId());
        schedulePredefinedAppointment(appointmentReservation);
    }

    public void checkPatientBanned(long patientId) {
        var patient = patientRepository.getOne(patientId);
        if (patient.isBanned()) {
            throw new PenaltiesException("Patient has 3 or more penalties.");
        }
    }

    @Transactional
    public void scheduleNewAppointment(CreatedAppointmentDto createdAppointmentDto, AppointmentType type) throws PersistenceException {
        var term = createdAppointmentDto.getTerm();

        var employee = employeeRepository.findById(createdAppointmentDto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(Employee.class.getSimpleName(), createdAppointmentDto.getEmployeeId()));
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
