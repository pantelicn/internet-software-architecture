package rs.ac.uns.ftn.isa.pharmacy.pharma.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.DrugReservationMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugReservationService;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/drugs/reservations")
public class DrugReservationController {
    private final DrugReservationService drugReservationService;

    public DrugReservationController(DrugReservationService drugReservationService) {
        this.drugReservationService = drugReservationService;
    }

    @Secured(Role.PHARMACIST)
    @GetMapping("{reservationId}")
    public ResponseEntity<?> get(@PathVariable long reservationId, HttpServletRequest request){
        try {
            var identity = HttpRequestUtil.getIdentity(request);
            var reservation = drugReservationService.getReserved(reservationId,identity.getRoleId());
            return ResponseEntity.ok(DrugReservationMapper.objectToDto(reservation));
        }
        catch (PersistenceException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Secured(Role.PHARMACIST)
    @DeleteMapping("dispense/{reservationId}")
    public ResponseEntity<?> dispense(@PathVariable long reservationId){
        try{
            drugReservationService.dispense(reservationId);
            return ResponseEntity.ok().build();
        }
        catch (PersistenceException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @Secured(Role.PATIENT)
    public List<DrugReservationDto> findPatientReservations(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return drugReservationService.findPatientReservations(identityProvider.getRoleId()).stream()
                .map(DrugReservationMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @Secured(Role.PATIENT)
    public void reserve(HttpServletRequest request, @RequestBody DrugReservationDto dto){
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        drugReservationService.reserve(DrugReservationMapper.dtoToObject(dto), identityProvider.getRoleId());
    }

    @DeleteMapping("{reservationId}")
    @Secured(Role.PATIENT)
    public void cancel(HttpServletRequest request, @PathVariable long reservationId) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        drugReservationService.cancelReservation(reservationId, identityProvider.getRoleId());
    }
}
