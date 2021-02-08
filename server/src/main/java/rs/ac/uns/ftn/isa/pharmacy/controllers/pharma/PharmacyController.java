package rs.ac.uns.ftn.isa.pharmacy.controllers.pharma;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PharmacyDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.PharmacyMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.pharma.PharmacyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pharmacies")
public class PharmacyController {
    private PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public List<PharmacyDto> getAllPharmacies() {
        return pharmacyService.findAll().stream()
                .map(PharmacyMapper::objectToDto)
                .collect(Collectors.toList());
    }
}
