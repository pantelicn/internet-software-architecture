package rs.ac.uns.ftn.isa.pharmacy.users.user.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingEmployeeDto;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.RatingEmployeeMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.RatingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/pharmacists")
    @Secured(Role.PATIENT)
    public List<RatingEmployeeDto> getPatientPharmacistHistory(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return ratingService.getPatientPharmacistHistory(identityProvider.getRoleId()).stream()
                .map(RatingEmployeeMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/dermatologists")
    @Secured(Role.PATIENT)
    public List<RatingEmployeeDto> getPatientDermatologistHistory(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return ratingService.getPatientDermatologistHistory(identityProvider.getRoleId()).stream()
                .map(RatingEmployeeMapper::objectToDto)
                .collect(Collectors.toList());
    }

}
