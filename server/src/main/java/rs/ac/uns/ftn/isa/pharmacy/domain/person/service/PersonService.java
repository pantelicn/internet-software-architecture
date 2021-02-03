package rs.ac.uns.ftn.isa.pharmacy.domain.person.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.dto.PersonUpdateDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidEntityException;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void update(PersonUpdateDto dto) throws EntityNotFoundException, InvalidEntityException {
        var optionalPerson = personRepository.findById(dto.getPersonId());
        if (optionalPerson.isEmpty())
            throw new EntityNotFoundException("Person");
        var person = optionalPerson.get();
        updatePersonFields(person, dto);
        personRepository.save(person);
    }

    /**
     * Checks for not-null fields within the DTO and overwrites existing values in the Person object with those values.
     * @throws InvalidEntityException If the address is present but malformed
     */
    private void updatePersonFields(Person person, PersonUpdateDto dto) throws InvalidEntityException {
        if (dto.getFirstName() != null) person.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) person.setLastName(dto.getLastName());
        if (dto.getGender() != null) person.setGender(dto.getGender());
        if (dto.getPhoneNumber() != null) person.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null) {
            dto.getAddress().validate();

            person.getAddress().setLatitude(dto.getAddress().getLatitude());
            person.getAddress().setLongitude(dto.getAddress().getLongitude());
            person.getAddress().setStreetName(dto.getAddress().getStreetName());
            person.getAddress().getCity().setName(dto.getAddress().getCity().getName());
            person.getAddress().getCity().setPostalCode(dto.getAddress().getCity().getPostalCode());
            person.getAddress().getCity().getCountry().setName(dto.getAddress().getCity().getCountry().getName());
            person.getAddress().getCity().getCountry().setCode(dto.getAddress().getCity().getCountry().getCode());
        }
    }
}
