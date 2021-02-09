package rs.ac.uns.ftn.isa.pharmacy.users.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RateableEntitiesDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingDrugDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingEmployeeDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingPharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.RateableEntitiesMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.RatingDrugMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.RatingEmployeeMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.RatingPharmacyMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.RatingService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    @Secured(Role.PATIENT)
    public RateableEntitiesDto getPatientPharmacistHistory(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return RateableEntitiesMapper.objectsToDto(
                ratingService.getPatientPharmacistHistory(identityProvider.getRoleId()),
                ratingService.getPatientDermatologistHistory(identityProvider.getRoleId()),
                ratingService.getPatientDrugHistory(identityProvider.getRoleId()),
                ratingService.getPatientPharmacyHistory(identityProvider.getRoleId())
        );
    }

    @PostMapping("/employee")
    @Secured(Role.PATIENT)
    @ResponseStatus(HttpStatus.OK)
    public void rateEmployee(HttpServletRequest request, @RequestBody RatingEmployeeDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        ratingService.rateEmployee(RatingEmployeeMapper.dtoToObject(dto), identityProvider.getRoleId());
    }

    @PostMapping("/drug")
    @Secured(Role.PATIENT)
    @ResponseStatus(HttpStatus.OK)
    public void rateDrug(HttpServletRequest request, @RequestBody RatingDrugDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        ratingService.rateDrug(RatingDrugMapper.dtoToObject(dto), identityProvider.getRoleId());
    }

    @PostMapping("/pharmacy")
    @Secured(Role.PATIENT)
    @ResponseStatus(HttpStatus.OK)
    public void ratePharmacy(HttpServletRequest request, @RequestBody RatingPharmacyDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        ratingService.ratePharmacy(RatingPharmacyMapper.dtoToObject(dto), identityProvider.getRoleId());
    }

}
