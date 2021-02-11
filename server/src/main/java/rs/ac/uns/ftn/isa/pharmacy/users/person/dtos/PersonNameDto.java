package rs.ac.uns.ftn.isa.pharmacy.users.person.dtos;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

public class PersonNameDto {
    private String firstName;
    private String lastName;

    public PersonNameDto(Person person){
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
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
}
