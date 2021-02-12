package rs.ac.uns.ftn.isa.pharmacy.promotion.dto;

import rs.ac.uns.ftn.isa.pharmacy.locale.domain.Address;

public class SubscriptionOverviewDto {

    private long pharmacyId;
    private String name;
    private Address address;
    private String description;

    public SubscriptionOverviewDto(long pharmacyId, String name, Address address, String description) {
        this.pharmacyId = pharmacyId;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
