package rs.ac.uns.ftn.isa.pharmacy.controllers.pharma;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.services.pharma.DrugService;

import java.util.List;

@RestController
@RequestMapping("api/drugs")
public class DrugController {
    private final DrugService service;

    DrugController(DrugService service) {
        this.service = service;
    }

    @GetMapping
    @Secured({Role.SYS_ADMIN, Role.PATIENT})
    public List<Drug> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Drug find(@PathVariable Long id) {
        return service.findById(id);
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
}
