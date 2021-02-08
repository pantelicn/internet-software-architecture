package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;

public class PharmacyDetailsDto {
    private long id;
    private String name;
    private Address address;
    private double rating;
    private String description;

    public PharmacyDetailsDto(long id, String name, Address address, double rating, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.description = description;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
