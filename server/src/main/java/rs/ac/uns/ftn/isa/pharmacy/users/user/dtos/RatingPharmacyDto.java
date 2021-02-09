package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;

public class RatingPharmacyDto {
    private long id;
    private String name;
    private double rating;
    private Address address;

    public RatingPharmacyDto(long id, String name, double rating, Address address) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.address = address;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
