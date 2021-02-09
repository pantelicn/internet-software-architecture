package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.repository.CredentialsRepository;
import rs.ac.uns.ftn.isa.pharmacy.locale.repository.CityRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.users.person.repository.PersonRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;

import java.util.UUID;

@Service
public class RegistrationService {

    private final CredentialsRepository credentialsRepository;
    private final PersonRepository personRepository;
    private final CityRepository cityRepository;
    private final EmailService emailService;

    public RegistrationService(
            CredentialsRepository credentialsRepository,
            PersonRepository personRepository,
            CityRepository cityRepository,
            EmailService emailService
    ){
        this.credentialsRepository = credentialsRepository;
        this.personRepository = personRepository;
        this.cityRepository = cityRepository;
        this.emailService = emailService;
    }

    public Person registerPatient(RegistrationDto dto)
            throws EntityExistsException, EntityNotFoundException, InvalidEntityException
    {
        return register(dto, Role.PATIENT);
    }

    public Person register(RegistrationDto dto, String role)
            throws InvalidEntityException, EntityExistsException, EntityNotFoundException
    {
        validateClientData(dto);
        if (credentialsRepository.findById(dto.getEmail()).isEmpty()) {
            Credentials credentials = mapToCredentials(dto, role);
            Person person = mapToPerson(dto);

            credentials.setPerson(person);
            person.setCredentials(credentials);

            Person savedPerson = personRepository.saveAndFlush(person);
            emailService.sendActivationMessage(credentials);
            return savedPerson;
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
        person.setPid(dto.getPid());

        return person;
    }

    private Credentials mapToCredentials(RegistrationDto dto, String role) {
        Credentials credentials = new Credentials();

        credentials.setActivated(false);
        credentials.setHasChangedInitialPassword(false);
        credentials.setUid(UUID.randomUUID());
        credentials.setEmail(dto.getEmail());
        credentials.setPassword(dto.getPassword());
        credentials.setRole(role);
        credentials.setUsername(dto.getUsername());

        return credentials;
    }

    private void validateClientData(RegistrationDto dto) throws InvalidEntityException, EntityNotFoundException {
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) throw new InvalidEntityException("First name");
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) throw new InvalidEntityException("Last name");
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) throw new InvalidEntityException("Username");
        if (dto.getPid() == null || dto.getPid().isEmpty()) throw new InvalidEntityException("PID");
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
