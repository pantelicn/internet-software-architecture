package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.finance.Price;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

public class StoredDrugDto {
    private long storedDrugId;
    private Price price;
    private Drug drug;
    private Pharmacy pharmacy;

    public StoredDrugDto(long storedDrugId, Price price, Drug drug, Pharmacy pharmacy) {
        this.storedDrugId = storedDrugId;
        this.price = price;
        this.drug = drug;
        this.pharmacy = pharmacy;
    }

    public long getStoredDrugId() {
        return storedDrugId;
    }

    public void setStoredDrugId(long storedDrugId) {
        this.storedDrugId = storedDrugId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
