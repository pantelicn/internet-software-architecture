package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.dtos.DrugReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.UserAccessException;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.StoredDrugRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {

    private final DrugRepository drugRepository;
    private final StoredDrugRepository storedDrugRepository;
    private final DrugReservationRepository drugReservationRepository;

    public DrugService(DrugRepository drugRepository,
                       StoredDrugRepository storedDrugRepository,
                       DrugReservationRepository drugReservationRepository
    ) {
        this.drugRepository = drugRepository;
        this.storedDrugRepository = storedDrugRepository;
        this.drugReservationRepository = drugReservationRepository;
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

    public void reserve(DrugReservation drugReservation, long patientId) {
        var patient = new Patient();
        patient.setId(patientId);
        drugReservation.setPatient(patient);
        updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), -drugReservation.getQuantity());
        try {
            drugReservationRepository.save(drugReservation);
        } catch (Exception e) {
            updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), drugReservation.getQuantity());
            throw e;
        }
    }

    public void cancelReservation(long drugReservationId, long patientId) {
        var drugReservation = drugReservationRepository.findById(drugReservationId)
                .orElseThrow(() -> new EntityNotFoundException(DrugReservation.class.getSimpleName(), drugReservationId));
        if (drugReservation.getPatient().getId() != patientId) {
            throw new UserAccessException();
        }
        updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), drugReservation.getQuantity());
        try {
            drugReservationRepository.deleteById(drugReservationId);
        } catch (Exception e) {
            updateStoredDrugQuantity(drugReservation.getStoredDrug().getId(), -drugReservation.getQuantity());
            throw e;
        }
    }

    private void updateStoredDrugQuantity(long storedDrugId, int quantity) {
        var storedDrug = storedDrugRepository.findById(storedDrugId)
                .orElseThrow(() -> new EntityNotFoundException(StoredDrug.class.getSimpleName(), storedDrugId));
        storedDrug.setQuantity(storedDrug.getQuantity() + quantity);
        storedDrugRepository.save(storedDrug);
    }
}
