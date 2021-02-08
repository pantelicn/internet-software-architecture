package rs.ac.uns.ftn.isa.pharmacy.promotion.model;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private Pharmacy pharmacy;
    @ManyToOne
    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
