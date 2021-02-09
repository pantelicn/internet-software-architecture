package rs.ac.uns.ftn.isa.pharmacy.schedule.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.*;
import rs.ac.uns.ftn.isa.pharmacy.schedule.mappers.AppointmentMapper;
import rs.ac.uns.ftn.isa.pharmacy.schedule.mappers.AppointmentTermMapper;
import rs.ac.uns.ftn.isa.pharmacy.schedule.services.AppointmentService;

import javax.servlet.http.HttpServletRequest;
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
    public List<AppointmentDto> getFreeExaminationsByPharmacy(@PathVariable long id) {
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
    public List<FreeAppointmentTermDto> getFreeExaminations(@RequestParam long pharmacyId, HttpServletRequest request) {
        return service.getFreeExaminations(pharmacyId,HttpRequestUtil.getIdentity(request).getRoleId())
                .stream()
                .map(AppointmentTermMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/patient")
    @Secured(Role.PATIENT)
    public List<AppointmentDto> getPatientExaminations(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return service
                .getPatientAppointments(identityProvider.getRoleId()).stream()
                .map(a -> AppointmentMapper.objectToDto(a))
                .collect(Collectors.toList());
    }
    @GetMapping("/cancel/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(Role.PATIENT)
    public void cancelAppointment(HttpServletRequest request, @PathVariable long id)
    {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        service.cancelPatientAppointment(identityProvider.getRoleId(), id);
    }
    @Secured(Role.DERMATOLOGIST)
    @GetMapping("/examinations/patient-history/{patientId}")
    public List<AppointmentHistoryEntryDto> getExaminationHistory(@PathVariable long patientId){
        return service.getPastExaminations(patientId)
                .stream()
                .map(AppointmentHistoryEntryDto::new)
                .collect(Collectors.toList());
    }
    @Secured(Role.PHARMACIST)
    @GetMapping("/counselings/patient-history")
    public List<AppointmentHistoryEntryDto> getCounselingHistory(@RequestParam long patientId,HttpServletRequest request){
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return service.getPastCounselingsInPharmacy(patientId,identityProvider.getRoleId())
                .stream()
                .map(AppointmentHistoryEntryDto::new)
                .collect(Collectors.toList());
    }
    @Secured({Role.DERMATOLOGIST,Role.PHARMACIST})
    @GetMapping("/upcoming")
    public List<UpcomingAppointmentEntryDto> getUpcomingAppointments(HttpServletRequest request){
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return service.getUpcomingAppointments(identityProvider.getRoleId())
                .stream()
                .map(UpcomingAppointmentEntryDto::new)
                .collect(Collectors.toList());
    }
    @Secured({Role.DERMATOLOGIST,Role.PHARMACIST})
    @GetMapping("/all")
    public List<UpcomingAppointmentEntryDto> getAllAppointments(HttpServletRequest request){
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);

        return service.findAll(identityProvider.getRoleId())
                .stream()
                .map(UpcomingAppointmentEntryDto::new)
                .collect(Collectors.toList());
    }

    @Secured({Role.DERMATOLOGIST})
    @PutMapping("/examinations/free-up/{examinationId}")
    public void freeUpExamination(@PathVariable long examinationId){
        service.freeUpExamination(examinationId);
    }

    @PutMapping("/counselings/free-up/{counselingId}")
    public void freeUpCounseling(@PathVariable long counselingId){
        service.freeUpCounseling(counselingId);
    }

    @PutMapping("/appointment-report")
    public void submitAppointmentReport(@RequestBody ReportSubmissionDto reportSubmissionDto){
        service.submitAppointmentReport(reportSubmissionDto);
    }

    @Secured(Role.PATIENT)
    @GetMapping("/patient/examination-history")
    public List<AppointmentDto> getPatientExaminationHistory(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return service.getPatientExaminationHistory(identityProvider.getRoleId()).stream()
                .map(AppointmentMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @Secured(Role.PATIENT)
    @GetMapping("/patient/counseling-history")
    public List<AppointmentDto> getPatientCounselingHistory(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return service.getPatientCounselingHistory(identityProvider.getRoleId()).stream()
                .map(AppointmentMapper::objectToDto)
                .collect(Collectors.toList());
    }
}
