package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.StoredDrugRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {

    private final DrugRepository drugRepository;
    private final StoredDrugRepository storedDrugRepository;

    public DrugService(DrugRepository drugRepository, StoredDrugRepository storedDrugRepository) {
        this.drugRepository = drugRepository;
        this.storedDrugRepository = storedDrugRepository;
    }

    public List<Drug> findAll() {
        return drugRepository.findAll();
    }

    public Drug findById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Drug.class.getSimpleName(), id));
    }

    public Drug create(Drug drug) {
        Optional<Drug> existingEntry = drugRepository.findById(drug.getId());
        if (existingEntry.isPresent()) {
            throw new EntityAlreadyExistsException("Drug", drug.getId());
        }
        return drugRepository.save(drug);
    }

    public Drug update(Drug newDrug, Long id) {
        if (drugRepository.existsById(id)) {
            newDrug.setId(id);
            return drugRepository.save(newDrug);
        }
        throw new EntityNotFoundException("Drug", newDrug.getId());
    }

    public void deleteById(Long id) {
        drugRepository.deleteById(id);
    }

    public List<StoredDrug> search(String name) {
        return storedDrugRepository.searchByName(name);
    }
}
