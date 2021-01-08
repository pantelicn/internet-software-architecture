package rs.ac.uns.ftn.isa.pharmacy.domain.users.user;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Person person;
    @Embedded
    private Credentials credentials;
    private boolean isActivated;
    private UUID uid;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public boolean isActivated() {
        return isActivated;
    }
    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public UUID getUid() {
        return uid;
    }
    public void setUid(UUID uid) {
        this.uid = uid;
    }
}
