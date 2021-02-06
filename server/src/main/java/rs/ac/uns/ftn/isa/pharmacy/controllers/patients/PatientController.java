package rs.ac.uns.ftn.isa.pharmacy.controllers.patients;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientProfilePreviewDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.PatientBasicInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.patients.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.services.pharma.DrugService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/patients")
public class PatientController {
    private final PatientService patientService;
    private final DrugService drugService;

    public PatientController(PatientService patientService, DrugService drugService) {
        this.patientService = patientService;
        this.drugService = drugService;
    }

    @GetMapping("/appointed-by/{employeeId}")
    public List<PatientBasicInfoDto> getAppointedPatients(@PathVariable long employeeId){
        return patientService.getAppointedBy(employeeId)
                .stream()
                .map(PatientBasicInfoMapper::objectToDto)
                .collect(Collectors.toList());
    }
    //TODO: Razmisliti da u buducnosti, mozda ne postoje maperi nego da se prosledjuje konstruktoru?
    @GetMapping("/profile-preview/{patientId}")
    public PatientProfilePreviewDto getPatientProfilePreview(@PathVariable long patientId){
        return new PatientProfilePreviewDto(patientService.get(patientId));
    }
    @GetMapping("/is-allergic")
    public ResponseEntity<?> checkAllergies(@RequestParam long patientId, @RequestParam long drugId){
        if( patientService.isAllergic(patientId,drugId) )
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(drugService.getAlternatives(drugId));
        else
            return ResponseEntity.ok().body(null);
    }


}
