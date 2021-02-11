package rs.ac.uns.ftn.isa.pharmacy.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface IdentityProvider {
    String getEmail();
    long getPersonId();
    long getRoleId();
    Collection<? extends GrantedAuthority> getAuthorities();
}
