package rs.ac.uns.ftn.isa.pharmacy.controllers.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.AppointmentReservationDTO;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.PatientOccupiedException;
import rs.ac.uns.ftn.isa.pharmacy.services.schedule.SchedulingService;

@RestController
@RequestMapping("api/schedule")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PutMapping("/predefined")
    public ResponseEntity<?> schedulePredefinedAppointment(@RequestBody AppointmentReservationDTO appointmentReservation){
        try {
            schedulingService.schedulePredefinedAppointment(appointmentReservation);
        }
        catch (PatientOccupiedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);


    }
}
