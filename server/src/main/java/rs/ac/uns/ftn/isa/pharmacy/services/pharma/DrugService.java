package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.repository.DrugRepository;

import java.util.List;

@Service
public class DrugService {

    private final DrugRepository repository;

    public DrugService(DrugRepository repository) {
        this.repository = repository;
    }

    public List<Drug> findAll() {
        return repository.findAll();
    }

    public Drug findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Drug.class.getSimpleName(), id));
    }

    public Drug save(Drug drug) {
        return repository.save(drug);
    }

    public Drug update(Drug newDrug, Long id) {
        return repository.findById(id)
                .map(drug -> {
                    drug.setAdditionalNotes(newDrug.getAdditionalNotes());
                    drug.setAlternatives(newDrug.getAlternatives());
                    drug.setDrugType(newDrug.getDrugType());
                    drug.setIngredients(newDrug.getIngredients());
                    drug.setName(newDrug.getName());
                    drug.setPrescriptionRequired(newDrug.isPrescriptionRequired());
                    drug.setIntakeType(newDrug.getIntakeType());
                    return repository.save(drug);
                })
                .orElseGet(() -> {
                    newDrug.setId(id);
                    return repository.save(newDrug);
                });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
