package rs.ac.uns.ftn.isa.pharmacy.domain.users.admin;

import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;

import javax.persistence.*;

@Entity
@Table(name="admins")
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
