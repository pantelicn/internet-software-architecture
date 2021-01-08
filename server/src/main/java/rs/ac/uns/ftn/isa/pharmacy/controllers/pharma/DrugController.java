package rs.ac.uns.ftn.isa.pharmacy.controllers.pharma;

import org.springframework.web.bind.annotation.*;
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
    List<Drug> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    Drug find(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    Drug create(@RequestBody Drug drug) {
        return service.create(drug);
    }

    @PutMapping
    Drug update(@RequestBody Drug drug) {
        return service.update(drug);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
