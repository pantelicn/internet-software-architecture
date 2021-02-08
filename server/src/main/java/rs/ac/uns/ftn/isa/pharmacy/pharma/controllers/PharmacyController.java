package rs.ac.uns.ftn.isa.pharmacy.pharma.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyAdminCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.PharmacyDetailsDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.PharmacyMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.PharmacyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pharmacies")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public List<PharmacyDetailsDto> getAllPharmacies() {
        return pharmacyService.findAll().stream()
                .map(PharmacyMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> create(@RequestBody PharmacyCreationDto dto) {
        try {
            pharmacyService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("admin")
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> addAdmin(@RequestBody PharmacyAdminCreationDto dto) {
        try {
            pharmacyService.addAdmin(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
