package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.repository.CredentialsRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.locale.Address;
import rs.ac.uns.ftn.isa.pharmacy.domain.locale.repository.AddressRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.locale.repository.CityRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidEntityException;

import java.util.UUID;

@Service
public class RegistrationService {

    private final CredentialsRepository credentialsRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    public RegistrationService(
            CredentialsRepository credentialsRepository,
            PersonRepository personRepository,
            AddressRepository addressRepository,
            CityRepository cityRepository
    ){
        this.credentialsRepository = credentialsRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public void register(RegistrationDto dto)
            throws InvalidEntityException, EntityExistsException, EntityNotFoundException
    {
        validateClientData(dto);
        if (credentialsRepository.findById(dto.getEmail()).isEmpty()) {
            Credentials credentials = mapToCredentials(dto);
            Person person = mapToPerson(dto);

            credentials.setPerson(person);

            Address savedAddress = addressRepository.saveAndFlush(person.getAddress());
            person.setAddress(savedAddress);
            personRepository.saveAndFlush(person);
            credentialsRepository.save(credentials);
        }
        else throw new EntityExistsException("Account");
    }

    private Person mapToPerson(RegistrationDto dto) {
        Person person = new Person();

        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setPhoneNumber(dto.getPhoneNumber());
        person.setGender(dto.getGender());
        person.setDateOfBirth(dto.getDateOfBirth());

        return person;
    }

    private Credentials mapToCredentials(RegistrationDto dto) {
        Credentials credentials = new Credentials();

        credentials.setActivated(false);
        credentials.setHasLoggedInBefore(false);
        credentials.setUid(UUID.randomUUID());
        credentials.setEmail(dto.getEmail());
        credentials.setPassword(dto.getPassword());
        credentials.setRole(Role.PATIENT);

        return credentials;
    }

    private void validateClientData(RegistrationDto dto) throws InvalidEntityException, EntityNotFoundException {
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) throw new InvalidEntityException("First name");
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) throw new InvalidEntityException("Last name");
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) throw new InvalidEntityException("Email address");
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) throw new InvalidEntityException("Password");
        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isEmpty()) throw new InvalidEntityException("Phone number");
        if (dto.getAddress() == null) throw new InvalidEntityException("Address");
        if (dto.getDateOfBirth() == null) throw new InvalidEntityException("Date of birth");
        if (dto.getGender() == null) throw new InvalidEntityException("Gender");
        dto.getAddress().validateSelf();
        if (dto.getAddress().getCity() == null) throw new InvalidEntityException("City");
        if (!cityRepository.existsById(dto.getAddress().getCity().getId())) throw new EntityNotFoundException("City");
    }
}
