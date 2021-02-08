package rs.ac.uns.ftn.isa.pharmacy.domain.promotion.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.promotion.dto.SubscriptionOrderDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.promotion.model.Subscription;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;

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
}
