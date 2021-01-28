package rs.ac.uns.ftn.isa.pharmacy.services.schedule;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.dtos.FreeAppointmentTermDto;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll() {
        return repository.findAll();
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

    // TODO: Refaktorisi da koristi mapper
    public List<FreeAppointmentTermDto> getFreeExaminations(long pharmacyId, long dermatologistId){
        var freeAppointmentTerms = new ArrayList<FreeAppointmentTermDto>();

        var freeAppointments= repository.findFreeExaminations()
                        .stream()
                        .filter(a -> a.getShift().getPharmacy().getId() == pharmacyId &&
                                    a.getShift().getEmployee().getId() == dermatologistId)
                        .collect(Collectors.toList());

        for(var appointment : freeAppointments)
            if(appointment.getTerm().isInFuture())
                freeAppointmentTerms.add(new FreeAppointmentTermDto(appointment.getTerm().getStart(), appointment.getTerm().getDuration(),appointment.getId()));

        return freeAppointmentTerms;
    }

}
