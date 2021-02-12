package rs.ac.uns.ftn.isa.pharmacy.locale.domain;

import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@SequenceGenerator(name = "addresses_seq", initialValue = 100, allocationSize = 1)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_seq")
    private long id;
    @ManyToOne()
    private City city;
    private String streetName;
    private double latitude;
    private double longitude;

    public void validate() throws InvalidEntityException {
        if (streetName == null) throw new InvalidEntityException("Address street name");
        city.validate();
    }

    public void validateSelf() throws InvalidEntityException {
        if (streetName == null) throw new InvalidEntityException("Address street name");
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return streetName + ", " + city.toString();
    }
}
