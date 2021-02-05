package rs.ac.uns.ftn.isa.pharmacy.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface IdentityProvider {
    String getEmail();
    long getUserId();
    Collection<? extends GrantedAuthority> getAuthorities();
}
