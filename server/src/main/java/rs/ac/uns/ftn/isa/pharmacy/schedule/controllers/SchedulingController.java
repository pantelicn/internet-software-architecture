package rs.ac.uns.ftn.isa.pharmacy.schedule.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.AppointmentType;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.CreatedAppointmentDto;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.PharmacistCounselingDto;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.PredefinedAppointmentReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.schedule.mappers.PharmacistCounselingMapper;
import rs.ac.uns.ftn.isa.pharmacy.schedule.services.SchedulingService;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @PutMapping("/predefined/patient")
    @Secured(Role.PATIENT)
    @ResponseStatus(HttpStatus.OK)
    public void schedulePredefinedAppointment(HttpServletRequest request, @RequestBody PredefinedAppointmentReservationDto appointmentReservation) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        appointmentReservation.setPatientId(identityProvider.getRoleId());
        schedulingService.schedulePredefinedAppointmentPatient(appointmentReservation);
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

    @PostMapping("/counseling/pharmacists")
    public List<PharmacistCounselingDto> findAvailablePharmacistsForCounseling(@RequestBody LocalDateTime dateTime) {
        return schedulingService.findAvailableShiftsForCounseling(dateTime).stream()
                .map(PharmacistCounselingMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/counseling/patient")
    @ResponseStatus(HttpStatus.OK)
    @Secured(Role.PATIENT)
    public void schedulePatientCounseling(HttpServletRequest request, @RequestBody CreatedAppointmentDto createdAppointmentDto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        createdAppointmentDto.setPatientId(identityProvider.getRoleId());
        createdAppointmentDto.setDuration(Duration.ofMinutes(30));
        schedulingService.scheduleNewAppointmentPatient(createdAppointmentDto, AppointmentType.Counseling);
    }

}
