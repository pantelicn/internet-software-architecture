package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;

public class PharmacistCounselingDto {
    private long pharmacyId;
    private String pharmacyName;
    private double pharmacyRating;
    private Address address;
    private Money counselingPrice;
    private long id;
    private String name;
    private String lastName;
    private double rating;

    public PharmacistCounselingDto(long pharmacyId,
                                   String pharmacyName,
                                   double pharmacyRating,
                                   Address address,
                                   Money counselingPrice,
                                   long id,
                                   String name,
                                   String lastName,
                                   double rating) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyRating = pharmacyRating;
        this.address = address;
        this.counselingPrice = counselingPrice;
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.rating = rating;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public double getPharmacyRating() {
        return pharmacyRating;
    }

    public void setPharmacyRating(double pharmacyRating) {
        this.pharmacyRating = pharmacyRating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Money getCounselingPrice() {
        return counselingPrice;
    }

    public void setCounselingPrice(Money counselingPrice) {
        this.counselingPrice = counselingPrice;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
