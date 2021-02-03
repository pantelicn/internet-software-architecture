package rs.ac.uns.ftn.isa.pharmacy.dtos;

import java.time.LocalDate;

public class DrugReservationDto {
    private long storedDrugId;
    private int quantity;
    private LocalDate pickUpBefore;

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
}
