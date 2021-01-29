package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.dtos.AppointmentReservationDTO;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;


@Service
public class SchedulingService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public SchedulingService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }
    @Transactional
    public void schedulePredefinedAppointment(AppointmentReservationDTO appointmentReservation) throws PatientOccupiedException{
        var appointment = appointmentRepository.getOne(appointmentReservation.getAppointmentId());
        var patient = patientRepository.getOne(appointmentReservation.getPatientId());

        if(!patient.canSchedule(appointment))
            throw new PatientOccupiedException(patient);

        appointment.setPatient(patient);
        appointmentRepository.save(appointment);


    }
}
