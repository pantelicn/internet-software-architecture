package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.finance.Price;

import java.time.LocalDate;

public class DrugReservationDto {
    private long storedDrugId;
    private int quantity;
    private LocalDate pickUpBefore;

    private long reservationId;
    private String name;
    private String manufacturer;
    private String pharmacyName;
    private Price price;

    public DrugReservationDto() {
    }

    public DrugReservationDto(long reservationId,
                              String name,
                              String manufacturer,
                              String pharmacyName,
                              Price price,
                              LocalDate pickUpBefore,
                              int quantity) {
        this.reservationId = reservationId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.pharmacyName = pharmacyName;
        this.price = price;
        this.pickUpBefore = pickUpBefore;
        this.quantity = quantity;

    }

    public long getStoredDrugId() {
        return storedDrugId;
    }

    public void setStoredDrugId(long storedDrugId) {
        this.storedDrugId = storedDrugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPickUpBefore() {
        return pickUpBefore;
    }

    public void setPickUpBefore(LocalDate pickUpBefore) {
        this.pickUpBefore = pickUpBefore;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
