package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.infrastructure.repository.DrugRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<Drug> existingEntry = repository.findById(drug.getId());
        if (existingEntry.isPresent()) {
            throw new EntityAlreadyExistsException("Drug", drug.getId());
        }
        return repository.save(drug);
    }

    public Drug update(Drug newDrug) {
        Optional<Drug> existingEntry =  repository.findById(newDrug.getId());
        if (existingEntry.isPresent()) {
            return repository.save(newDrug);
        }
        throw new EntityNotFoundException("Drug", newDrug.getId());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
