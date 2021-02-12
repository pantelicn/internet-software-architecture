package rs.ac.uns.ftn.isa.pharmacy.users.person.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="persons")
@SequenceGenerator(name = "persons_seq", initialValue = 100, allocationSize = 1)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_seq")
    private long id;
    private String pid;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Credentials credentials;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }


    public String getFullName() {

        return this.getFirstName() + " " + this.getLastName();

    }
}
