package rs.ac.uns.ftn.isa.pharmacy.dtos;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Price;

public class DrugDto {
    private String name;
    private long quantity;
    private Price price;
    private String manufacturer;

    public DrugDto(String name, long quantity, Price price, String manufacturer) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
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
}
