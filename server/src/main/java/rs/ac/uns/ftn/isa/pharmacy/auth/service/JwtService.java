package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.AuthToken;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.IdentityProvider;


@SuppressWarnings("all")
@Service
public class JwtService {
    private final Algorithm _algorithm;
    private final String _secret;

    public JwtService() {
        this._secret = "9zxc09ASd912ekaskd9zxc90ASAS09zxcASdklj12e90askzxkcajhgjh3r89as8909190e12kad;";
        _algorithm = Algorithm.HMAC256(this._secret);
    }

    private static final String IDENTITY_CLAIM_KEY = "identity";

    public IdentityProvider decrypt(String jwt) throws IllegalArgumentException {
        JWTVerifier verifier = JWT.require(_algorithm).build();
        try {
            verifier.verify(jwt);
        }
        catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Bad signature.");
        }
        DecodedJWT decodedJWT = JWT.decode(jwt);
        return deserializeIdentityToken(decodedJWT.getClaim(IDENTITY_CLAIM_KEY).asString());
    }

    public String encrypt(IdentityProvider identityProvider) {
        return JWT.create()
                .withClaim(IDENTITY_CLAIM_KEY, serializeIdentityToken(identityProvider))
                .sign(_algorithm);
    }

    private static Gson gson = new Gson();
    private String serializeIdentityToken(IdentityProvider identityProvider) {
        return gson.toJson(identityProvider);
    }
    private AuthToken deserializeIdentityToken(String serializedToken) {
        return gson.fromJson(serializedToken, AuthToken.class);
    }
}
