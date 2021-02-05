package rs.ac.uns.ftn.isa.pharmacy.controllers.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.dtos.*;
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
    public List<FreeAppointmentTermDto> getFreeExaminations(@RequestParam long pharmacyId, @RequestParam long dermatologistId) {
        return service.getFreeExaminations(pharmacyId,dermatologistId)
                .stream()
                .map(AppointmentTermMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/patient/{id}")
    public List<AppointmentDto> getPatientExaminations(@PathVariable long id) {
        return service
                .getPatientAppointments(id).stream()
                .map(a -> AppointmentMapper.objectToDto(a))
                .collect(Collectors.toList());
    }
    @GetMapping("/cancel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void cancelAppointment(@PathVariable long id)
    {
        //TODO - Get patient id from header
        service.cancelPatientAppointment(1, id);
    }
    @GetMapping("/examinations/patient-history/{patientId}")
    public List<AppointmentHistoryEntryDto> getExaminationHistory(@PathVariable long patientId){
        return service.getPastExaminations(patientId)
                .stream()
                .map(AppointmentHistoryEntryDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/counselings/patient-history")
    public List<AppointmentHistoryEntryDto> getCounselingHistory(@RequestParam long patientId,@RequestParam long pharmacistId){
        //TODO - Get pharmacist id from header
        return service.getPastCounselingsInPharmacy(patientId,pharmacistId)
                .stream()
                .map(AppointmentHistoryEntryDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/upcoming/{employeeId}")
    public List<UpcomingAppointmentEntryDto> getUpcomingAppointments(@PathVariable long employeeId){
        return service.getUpcomingAppointments(employeeId)
                .stream()
                .map(UpcomingAppointmentEntryDto::new)
                .collect(Collectors.toList());
    }
    @PutMapping("/examinations/free-up/{examinationId}")
    public void freeUpExamination(@PathVariable long examinationId){
        service.freeUpExamination(examinationId);
    }

    @PutMapping("/appointment-report")
    public void submitAppointmentReport(@RequestBody ReportSubmissionDto reportSubmissionDto){
        service.submitAppointmentReport(reportSubmissionDto);
    }
}
