package rs.ac.uns.ftn.isa.pharmacy.controllers.schedule;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.dtos.CreatedAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PredefinedAppointmentReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.services.schedule.SchedulingService;

import javax.persistence.PersistenceException;

@RestController
@RequestMapping("api/schedule")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }


    @PutMapping("/predefined")
    public ResponseEntity<?> schedulePredefinedAppointment(@RequestBody PredefinedAppointmentReservationDto appointmentReservation){
        try {
            schedulingService.schedulePredefinedAppointment(appointmentReservation);
        }
        catch (PatientOccupiedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/examination")
    public ResponseEntity<?> scheduleNewExamination(@RequestBody CreatedAppointmentDto createdAppointmentDto){
        try {
            schedulingService.scheduleNewExamination(createdAppointmentDto);
        }
        catch (PersistenceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }


}
