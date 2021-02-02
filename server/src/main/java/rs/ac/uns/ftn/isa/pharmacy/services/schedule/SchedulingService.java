package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.AppointmentType;
import rs.ac.uns.ftn.isa.pharmacy.dtos.CreatedAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PredefinedAppointmentReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.AppointmentTimeException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EmployeeOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EmployeeShiftException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;
import rs.ac.uns.ftn.isa.pharmacy.services.notifiers.EmailService;

import javax.persistence.PersistenceException;


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
}
