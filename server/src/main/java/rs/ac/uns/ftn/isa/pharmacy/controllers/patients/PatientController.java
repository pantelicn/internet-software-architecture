package rs.ac.uns.ftn.isa.pharmacy.controllers.patients;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.dtos.AllergiesDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientProfilePreviewDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.PatientBasicInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.mappers.PatientInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.patients.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.services.pharma.DrugService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Secured({Role.PHARMACIST,Role.DERMATOLOGIST})
    @GetMapping("/appointed")
    public List<PatientBasicInfoDto> getAppointedPatients(HttpServletRequest request){
        IdentityProvider provider = HttpRequestUtil.getIdentity(request);
        return patientService.getAppointedBy(provider.getRoleId())
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

    @GetMapping
    @Secured(Role.PATIENT)
    public PatientInfoDto getPatientInfo(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return PatientInfoMapper.objectToDto(patientService.get(identityProvider.getRoleId()));
    }

    @PutMapping("allergies")
    @Secured(Role.PATIENT)
    @ResponseStatus(HttpStatus.OK)
    public void updateAllergies(HttpServletRequest request, @RequestBody List<Drug> drugs) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        patientService.updateAllergies(drugs, identityProvider.getRoleId());
    }
}
