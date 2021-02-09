package rs.ac.uns.ftn.isa.pharmacy.promotion.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.promotion.dto.SubscriptionOverviewDto;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.users.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.promotion.dto.SubscriptionOrderDto;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Subscription;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;

import java.util.Optional;

@Component
public class SubscriptionMapper {

    private final PharmacyRepository pharmacyRepository;
    private final PersonRepository personRepository;

    public SubscriptionMapper(PharmacyRepository pharmacyRepository, PersonRepository personRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.personRepository = personRepository;
    }

    public Subscription dtoToObject(SubscriptionOrderDto dto) throws InvalidForeignKeyException {
        Subscription subscription = new Subscription();

        Optional<Person> optionalPerson = personRepository.findById(dto.getPersonId());
        if (optionalPerson.isEmpty()) throw new InvalidForeignKeyException("Person");
        Optional<Pharmacy> optionalPharmacy = pharmacyRepository.findById(dto.getPharmacyId());
        if (optionalPharmacy.isEmpty()) throw new InvalidForeignKeyException("Pharmacy");

        subscription.setPerson(optionalPerson.get());
        subscription.setPharmacy(optionalPharmacy.get());

        return subscription;
    }

    public static SubscriptionOverviewDto objectToDto(Subscription subscription) {
        return new SubscriptionOverviewDto(
                subscription.getPharmacy().getId(),
                subscription.getPharmacy().getName(),
                subscription.getPharmacy().getAddress(),
                subscription.getPharmacy().getDescription()
        );
    }
}
