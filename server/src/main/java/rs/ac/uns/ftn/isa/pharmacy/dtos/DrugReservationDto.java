package rs.ac.uns.ftn.isa.pharmacy.dtos;

public class DrugReservationDto {
    private long storedDrugId;
    private int quantity;

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
}
