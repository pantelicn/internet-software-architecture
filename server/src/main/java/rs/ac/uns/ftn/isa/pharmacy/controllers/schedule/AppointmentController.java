package rs.ac.uns.ftn.isa.pharmacy.controllers.schedule;

import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.dtos.FreeAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.FreeAppointmentTermDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.AppointmentMapper;
import rs.ac.uns.ftn.isa.pharmacy.mappers.AppointmentTermMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.schedule.AppointmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Appointment> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public List<FreeAppointmentDto> getFreeExaminationsByPharmacy(@PathVariable long id) {
        return service
                .getFreeExaminations(id).stream()
                .map(AppointmentMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public Appointment createFreeExamination(@RequestBody Appointment appointment) {
        return service.createFreeExamination(appointment);
    }

    @GetMapping("/free-examinations")
    public List<FreeAppointmentTermDto> getFreeExaminations(@RequestParam long pharmacyId, @RequestParam long dermatologistId) {
        return service.getFreeExaminations(pharmacyId,dermatologistId)
                .stream()
                .map(AppointmentTermMapper::objectToDto)
                .collect(Collectors.toList());
    }

}
