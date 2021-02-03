package rs.ac.uns.ftn.isa.pharmacy.controllers.patients;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientProfilePreviewDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.PatientBasicInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.PatientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/appointed-by/{employeeId}")
    public List<PatientBasicInfoDto> getAppointedPatients(@PathVariable long employeeId){
        return service.getAppointedBy(employeeId)
                .stream()
                .map(PatientBasicInfoMapper::objectToDto)
                .collect(Collectors.toList());
    }
    //TODO: Razmisliti da u buducnosti, mozda ne postoje maperi nego da se prosledjuje konstruktoru?
    @GetMapping("/profile-preview/{patientId}")
    public PatientProfilePreviewDto getPatientProfilePreview(@PathVariable long patientId){
        return new PatientProfilePreviewDto(service.get(patientId));
    }


}
