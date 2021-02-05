package rs.ac.uns.ftn.isa.pharmacy.controllers.pharma;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.dtos.DrugDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.DrugReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.DrugReservationMapper;
import rs.ac.uns.ftn.isa.pharmacy.mappers.StoredDrugMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.pharma.DrugService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/drugs")
public class DrugController {
    private final DrugService service;

    DrugController(DrugService service) {
        this.service = service;
    }

    @GetMapping
    public List<Drug> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Drug find(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/search/{name}")
    public List<DrugDto> search(@PathVariable String name) {
        return service.search(name).stream()
                .map(d -> StoredDrugMapper.objectToDto(d))
                .collect(Collectors.toList());
    }

    @PostMapping
    Drug create(@RequestBody Drug drug) {
        return service.create(drug);
    }

    @PutMapping("{id}")
    public Drug update(@RequestBody Drug drug, @PathVariable Long id) {
        return service.update(drug, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/reservations/{patientId}")
    public List<DrugReservationDto> findPatientReservations(@PathVariable long patientId) {
        var list = service.findPatientReservations(patientId).stream()
                .map(r -> DrugReservationMapper.objectToDto(r))
                .collect(Collectors.toList());
        return list;
    }

    @PostMapping("/reservations/{patientId}")
    public void reserve(@RequestBody DrugReservationDto dto, @PathVariable long patientId){
        service.reserve(DrugReservationMapper.dtoToObject(dto), patientId);
    }

    @DeleteMapping("/reservations/{reservationId}")
    public void cancel(@PathVariable long reservationId) {
        //TODO - get patient from headers.
        service.cancelReservation(reservationId, 1);
    }
}
