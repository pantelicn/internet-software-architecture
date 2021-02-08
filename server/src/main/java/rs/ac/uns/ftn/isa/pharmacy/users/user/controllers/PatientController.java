package rs.ac.uns.ftn.isa.pharmacy.users.user.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.PatientBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.PatientInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.PatientProfilePreviewDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.PatientBasicInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.PatientInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugService;

import javax.servlet.http.HttpServletRequest;
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
