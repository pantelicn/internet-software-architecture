package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.dtos.DrugNonExistentMailInfo;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.DrugNotAvailableException;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.DrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.StoredDrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.services.notifiers.EmailService;

@Service
public class StoredDrugService {
    private final StoredDrugRepository storedDrugRepository;
    private final PharmacyRepository pharmacyRepository;
    private final DrugRepository drugRepository;
    private final EmailService emailService;

    public StoredDrugService(StoredDrugRepository storedDrugRepository, PharmacyRepository pharmacyRepository,
                             DrugRepository drugRepository, EmailService emailService) {
        this.storedDrugRepository = storedDrugRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.drugRepository = drugRepository;
        this.emailService = emailService;
    }

    public void checkAvailability(long pharmacyId,long drugId) throws DrugNotAvailableException{
        validateDrugExisting(pharmacyId, drugId);
        validateDrugAvailable(pharmacyId, drugId);
    }

    private void validateDrugAvailable(long pharmacyId, long drugId) throws DrugNotAvailableException {
        if(!isAvailable(pharmacyId, drugId)){
            var drug= storedDrugRepository.getOneFromPharmacy(pharmacyId, drugId);
            emailService.sendInsufficientDrugQuantityMessage(drug);
            throw new DrugNotAvailableException();
        }
    }

    private void validateDrugExisting(long pharmacyId, long drugId) throws DrugNotAvailableException{
        if(!existsInPharmacy(pharmacyId, drugId)){
            emailService.sendDrugNonExistentMessage(new DrugNonExistentMailInfo(drugRepository.getOne(drugId),
                    pharmacyRepository.getPharmacyAdmin(pharmacyId)));
            throw new DrugNotAvailableException();
        }
    }

    private boolean existsInPharmacy(long pharmacyId, long drugId) {
        return this.storedDrugRepository.getOneFromPharmacy(pharmacyId, drugId) != null;
    }

    private boolean isAvailable(long pharmacyId, long drugId){
        return this.storedDrugRepository.quantityInPharmacy(pharmacyId,drugId) > 0;
    }
}
