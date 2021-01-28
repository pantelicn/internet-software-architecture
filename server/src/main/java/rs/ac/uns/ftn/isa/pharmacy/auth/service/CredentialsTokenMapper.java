package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import rs.ac.uns.ftn.isa.pharmacy.auth.model.AuthToken;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;

public class CredentialsTokenMapper {
    private static CredentialsTokenMapper _instance;

    public static CredentialsTokenMapper getInstance() {
        if (_instance == null)
            _instance = new CredentialsTokenMapper();
        return _instance;
    }

    public AuthToken createAuthToken(Credentials credentials) {
        AuthToken token = new AuthToken();
        token.setEmail(credentials.getEmail());
        token.setRole("admin");
        token.setUserId(credentials.getPerson().getId());
        return token;
    }

}
