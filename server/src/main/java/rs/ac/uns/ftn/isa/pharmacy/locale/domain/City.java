package rs.ac.uns.ftn.isa.pharmacy.locale.domain;

import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String postalCode;
    @ManyToOne
    private Country country;

    public void validate() throws InvalidEntityException {
        if (name == null) throw new InvalidEntityException("City name");
        if (postalCode == null) throw new InvalidEntityException("City postal code");
        if (country == null) throw new InvalidEntityException("Country");
        country.validate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return postalCode + " " + name + ", " + country.toString();
    }
}
