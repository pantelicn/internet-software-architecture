package rs.ac.uns.ftn.isa.pharmacy.promotion.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.promotion.dto.PromotionCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.promotion.dto.SubscriptionOrderDto;
import rs.ac.uns.ftn.isa.pharmacy.promotion.mapper.PromotionMapper;
import rs.ac.uns.ftn.isa.pharmacy.promotion.mapper.SubscriptionMapper;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Promotion;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Subscription;
import rs.ac.uns.ftn.isa.pharmacy.promotion.repository.PromotionRepository;
import rs.ac.uns.ftn.isa.pharmacy.promotion.repository.SubscriptionRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;

import java.util.List;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionMapper subscriptionMapper;
    private final PromotionMapper promotionMapper;

    private final EmailService emailService;

    public PromotionService(
            PromotionRepository promotionRepository,
            SubscriptionRepository subscriptionRepository,
            SubscriptionMapper subscriptionMapper,
            PromotionMapper promotionMapper,
            EmailService emailService
    ){
        this.promotionRepository = promotionRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.promotionMapper = promotionMapper;
        this.emailService = emailService;
    }

    public void subscribe(SubscriptionOrderDto dto) throws InvalidForeignKeyException {
        if (subscriptionRepository.countByPharmacyAndPerson(dto.getPharmacyId(), dto.getPersonId()) > 0)
            throw new EntityAlreadyExistsException("Subscription");
        Subscription subscription = subscriptionMapper.dtoToObject(dto);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(SubscriptionOrderDto dto) throws EntityNotFoundException {
        Subscription subscription =
                subscriptionRepository.getByPharmacyAndPerson(dto.getPharmacyId(), dto.getPersonId());
        if (subscription == null) throw new EntityNotFoundException("Subscription");
        subscriptionRepository.delete(subscription);
        subscriptionRepository.flush();
    }

    public void create(PromotionCreationDto dto) throws InvalidEntityException {
        Promotion promotion = promotionMapper.dtoToObject(dto);
        promotion.validate();
        promotionRepository.save(promotion);

        List<Subscription> subscriptions = subscriptionRepository.findByPharmacy(dto.getPharmacyId());
        for (var subscription : subscriptions) {
            emailService.sendPromotionMessage(promotion, subscription);
        }
    }

    public boolean isPatientSubscribed(long personId, long pharmacyId) {
        return subscriptionRepository.countByPharmacyAndPerson(pharmacyId, personId) > 0;
    }

    public List<Subscription> getSubscribedPharmaciesForPatient(long personId) {
        return subscriptionRepository.getByPerson(personId);
    }
}
