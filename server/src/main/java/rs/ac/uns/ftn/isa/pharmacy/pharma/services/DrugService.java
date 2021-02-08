package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.UserAccessException;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PatientRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.DrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.DrugReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.StoredDrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {

    private final DrugRepository drugRepository;
    private final StoredDrugRepository storedDrugRepository;

    public DrugService(DrugRepository drugRepository,
                       StoredDrugRepository storedDrugRepository
    ) {
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

    public Drug create(DrugCreationDto dto) throws MessageException {
        if (dto.getDrug() == null) throw new InvalidEntityException("Drug");
        dto.getDrug().validate();

        if (drugRepository.existsById(dto.getDrug().getId()))
            throw new EntityExistsException(dto.getDrug().getName());

        if (dto.getDrug().getAlternatives() != null) {
            for (var alt : dto.getDrug().getAlternatives()) {
                Optional<Drug> optionalDrug = drugRepository.findById(alt.getId());
                if (optionalDrug.isEmpty())
                    throw new MessageException("Alternative mentioned is nonexistent [" + alt.getId() + "].");
                dto.getDrug().getAlternatives().add(optionalDrug.get());
            }
        }

        return drugRepository.save(dto.getDrug());
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

    public List<StoredDrug> searchByName(String name) {
        return storedDrugRepository.searchByName(name);
    }

    public List<Drug> getAlternatives(long drugId) {
        return drugRepository.getOne(drugId).getAlternatives();
    }
}
