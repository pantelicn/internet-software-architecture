package rs.ac.uns.ftn.isa.pharmacy.domain.person.dto;

import rs.ac.uns.ftn.isa.pharmacy.domain.locale.Address;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Gender;

public class PersonUpdateDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private Address address;
    private long personId;

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

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
