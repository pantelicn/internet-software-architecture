package rs.ac.uns.ftn.isa.pharmacy.domain.pharma.dto;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.domain.locale.Address;

public class PharmacyCreationDto {
    private String name;
    private Address address;
    private String description;
    private Money counselingPrice;

    public Money getCounselingPrice() {
        return counselingPrice;
    }

    public void setCounselingPrice(Money counselingPrice) {
        this.counselingPrice = counselingPrice;
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