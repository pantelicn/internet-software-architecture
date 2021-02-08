package rs.ac.uns.ftn.isa.pharmacy.users.person.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.users.person.dtos.PersonUpdateDto;
import rs.ac.uns.ftn.isa.pharmacy.users.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.person.dtos.CredentialsDto;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void updateCredentials(CredentialsDto credentialsDto, long personId) throws EntityNotFoundException {
        var person = personRepository.
                findById(personId).
                orElseThrow(() -> new EntityNotFoundException(Person.class.getSimpleName()));
        var personCredentials = mapCredentials(person,credentialsDto);
        person.setCredentials(personCredentials);
        personRepository.save(person);
    }

    public Credentials mapCredentials(Person person, CredentialsDto credentialsDto){
        var personCredentials = person.getCredentials();
        personCredentials.setEmail(credentialsDto.getEmail());
        personCredentials.setPassword(credentialsDto.getPassword());
        personCredentials.setUsername(credentialsDto.getUsername());
        return personCredentials;
    }

    public Person get(long personId) throws EntityNotFoundException {
        return personRepository
                .findById(personId)
                .orElseThrow(() -> new EntityNotFoundException(Person.class.getSimpleName()));
    }

    public void update(PersonUpdateDto dto) throws EntityNotFoundException, InvalidEntityException {
        var optionalPerson = personRepository.findById(dto.getId());
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
        if (dto.getPid() != null) person.setPid(dto.getPid());
        if (dto.getDateOfBirth() != null) person.setDateOfBirth(dto.getDateOfBirth());
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
