package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import rs.ac.uns.ftn.isa.pharmacy.domain.locale.Address;

import java.util.List;

public class Pharmacy {
    private String name;
    private Address address;
    private double averageRating;
    private List<StoredDrug> storedDrugs;

    public Pharmacy() {
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<StoredDrug> getStoredDrugs() {
        return storedDrugs;
    }

    public void setStoredDrugs(List<StoredDrug> storedDrugs) {
        this.storedDrugs = storedDrugs;
    }
}
