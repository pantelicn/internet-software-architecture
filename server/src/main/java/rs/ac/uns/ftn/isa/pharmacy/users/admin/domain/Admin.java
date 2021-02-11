package rs.ac.uns.ftn.isa.pharmacy.users.admin.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

import javax.persistence.*;

@Entity
@Table(name="admins")
@SequenceGenerator(name = "admin_seq", initialValue = 100)
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    private long id;
    @OneToOne
    private Person person;
    private AdminType adminType;
    @OneToOne(mappedBy = "pharmacyAdmin")
    private Pharmacy pharmacy;

    public AdminType getAdminType() {
        return adminType;
    }

    public void setAdminType(AdminType adminType) {
        this.adminType = adminType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
