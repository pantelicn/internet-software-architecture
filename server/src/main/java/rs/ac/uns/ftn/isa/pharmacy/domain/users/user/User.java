package rs.ac.uns.ftn.isa.pharmacy.domain.users.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Person person;
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
