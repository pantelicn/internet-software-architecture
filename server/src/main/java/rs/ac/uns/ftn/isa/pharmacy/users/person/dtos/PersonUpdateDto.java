package rs.ac.uns.ftn.isa.pharmacy.users.person.dtos;

import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Gender;

import java.time.LocalDate;

public class PersonUpdateDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private Address address;
    private LocalDate dateOfBirth;
    private String pid;
    private long id;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
