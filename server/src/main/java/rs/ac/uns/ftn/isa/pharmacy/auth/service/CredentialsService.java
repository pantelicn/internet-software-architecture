package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.PassChangeDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.repository.CredentialsRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.IncorrectPasswordException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.PasswordTooShortException;

import java.util.UUID;

@Service
public class CredentialsService {

    private final CredentialsRepository credentialsRepository;

    public CredentialsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    public void logLogin(String email) {
        Credentials credentials = credentialsRepository.findById(email).get();
        if (!credentials.hasLoggedInBefore()) {
            credentials.setHasLoggedInBefore(true);
            credentialsRepository.save(credentials);
        }
    }

    public boolean hasLoggedIn(String email) {
        Credentials credentials = credentialsRepository.findById(email).get();
        return credentials.hasLoggedInBefore();
    }

    public void changePassword(String email, PassChangeDto dto) throws IncorrectPasswordException, PasswordTooShortException {
        if (dto.getOldPassword() == null || dto.getNewPassword() == null || dto.getNewPassword().length() < 6)
            throw new PasswordTooShortException();
        Credentials credentials = credentialsRepository.findById(email).get();
        if (!credentials.getPassword().contentEquals(dto.getOldPassword()))
            throw new IncorrectPasswordException();
        credentials.setPassword(dto.getNewPassword());
        credentialsRepository.save(credentials);
    }

    public void activate(UUID uuid) throws EntityNotFoundException {
        Credentials credentials = credentialsRepository.getByUuid(uuid.toString());
        if (credentials == null) throw new EntityNotFoundException("Account");
        credentials.setActivated(true);
        credentialsRepository.save(credentials);
    }
}
