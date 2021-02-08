package rs.ac.uns.ftn.isa.pharmacy.pharma.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DrugNotAvailableException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.StoredDrugService;

@RestController
@RequestMapping("api/stored-drugs")
public class StoredDrugController {
    private final StoredDrugService storedDrugService;
    private final DrugService drugService;

    public StoredDrugController(StoredDrugService storedDrugService, DrugService drugService) {
        this.storedDrugService = storedDrugService;
        this.drugService = drugService;
    }

    @GetMapping("/is-available")
    public ResponseEntity<?> checkAvailability(@RequestParam long pharmacyId, @RequestParam long drugId){
        try {
            storedDrugService.checkAvailability(pharmacyId,drugId);
        }
        catch (DrugNotAvailableException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(drugService.getAlternatives(drugId));
        }
        return ResponseEntity.ok().body(null);
    }
}
