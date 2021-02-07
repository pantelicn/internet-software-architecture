package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.PassChangeDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.repository.CredentialsRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.IncorrectPasswordException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.PasswordTooShortException;

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
        var oldPassword = credentialsRepository.getOne(email).getPassword();
        if (dto.getPassword() == null || dto.getPassword().length() < Credentials.MIN_PASSWORD_LEN)
            throw new PasswordTooShortException();
        Credentials credentials = credentialsRepository.findById(email).get();
        if (!credentials.getPassword().contentEquals(oldPassword))
            throw new IncorrectPasswordException();
        credentials.setPassword(dto.getPassword());
        credentialsRepository.save(credentials);
    }
}
