package rs.ac.uns.ftn.isa.pharmacy.controllers.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.AppointmentType;
import rs.ac.uns.ftn.isa.pharmacy.dtos.CreatedAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PredefinedAppointmentReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.services.schedule.SchedulingService;

@RestController
@RequestMapping("api/schedule")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }


    @PutMapping("/predefined")
    @Secured({Role.PATIENT,Role.DERMATOLOGIST})
    public ResponseEntity<?> schedulePredefinedAppointment(@RequestBody PredefinedAppointmentReservationDto appointmentReservation){
        schedulingService.schedulePredefinedAppointment(appointmentReservation);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/examination")
    @Secured(Role.DERMATOLOGIST)
    public ResponseEntity<?> scheduleNewExamination(@RequestBody CreatedAppointmentDto createdAppointmentDto){
        schedulingService.scheduleNewAppointment(createdAppointmentDto, AppointmentType.Examination);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
    @Secured(Role.PHARMACIST)
    @PostMapping("/counseling")
    public ResponseEntity<?> scheduleNewCounseling(@RequestBody CreatedAppointmentDto createdAppointmentDto){
        schedulingService.scheduleNewAppointment(createdAppointmentDto, AppointmentType.Counseling);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }


}
