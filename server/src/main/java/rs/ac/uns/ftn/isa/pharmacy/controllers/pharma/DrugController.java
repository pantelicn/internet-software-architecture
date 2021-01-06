package rs.ac.uns.ftn.isa.pharmacy.controllers.pharma;

import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.repository.DrugRepository;
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
    Drug add(@RequestBody Drug drug) {
        return service.save(drug);
    }

    @PutMapping("{id}")
    Drug update(@RequestBody Drug drug, @PathVariable Long id) {
        return service.update(drug, id);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
