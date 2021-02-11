package rs.ac.uns.ftn.isa.pharmacy.auth.model;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credentials")
public class Credentials implements UserDetails {
    @Id
    private String email;
    @Column(unique = true)
    @Type(type = "uuid-char")
    private UUID uid;
    private String username;
    private String password;
    private String role;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;
    private boolean isActivated;
    private boolean hasChangedInitialPassword;

    public static int MIN_PASSWORD_LEN = 6;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivated;
    }

    // Get set

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean hasLoggedInBefore() {
        return hasChangedInitialPassword;
    }

    public void setHasChangedInitialPassword(boolean hasChangedInitialPassword) {
        this.hasChangedInitialPassword = hasChangedInitialPassword;
    }
}
