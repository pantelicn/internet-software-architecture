package rs.ac.uns.ftn.isa.pharmacy.auth.model;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Person;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    private String email;
    @Column(unique = true)
    private UUID uid;
    private String password;
    private String role;

    @OneToOne
    private Person person;
    private boolean isActivated;

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
}
