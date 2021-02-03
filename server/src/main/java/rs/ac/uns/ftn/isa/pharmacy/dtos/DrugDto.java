package rs.ac.uns.ftn.isa.pharmacy.dtos;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Price;

public class DrugDto {
    private long storedDrugId;
    private long drugId;
    private String name;
    private long quantity;
    private Price price;
    private String manufacturer;
    private long pharmacyId;
    private String pharmacyName;

    public DrugDto(long storedDrugId, long drugId, String name, long quantity, Price price, String manufacturer, long pharmacyId, String pharmacyName) {
        this.storedDrugId = storedDrugId;
        this.drugId = drugId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
    }

    public long getStoredDrugId() {
        return storedDrugId;
    }

    public void setStoredDrugId(long storedDrugId) {
        this.storedDrugId = storedDrugId;
    }

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
}
