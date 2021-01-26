package rs.ac.uns.ftn.isa.pharmacy.controllers.schedule;

import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.dtos.FreeAppointmentTermDto;
import rs.ac.uns.ftn.isa.pharmacy.services.schedule.AppointmentService;

import java.util.List;

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
    public List<Appointment> getFreeExaminationsByPharmacy(@PathVariable long id) {
        return service.getFreeExaminations(id);
    }

    @PostMapping()
    public Appointment createFreeExamination(@RequestBody Appointment appointment) {
        return service.createFreeExamination(appointment);
    }

    @GetMapping("/free-examinations")
    public List<FreeAppointmentTermDto> getFreeExaminations(@RequestParam long pharmacyId, @RequestParam long dermatologistId) {
        return service.getFreeExaminations(pharmacyId,dermatologistId);
    }
}
