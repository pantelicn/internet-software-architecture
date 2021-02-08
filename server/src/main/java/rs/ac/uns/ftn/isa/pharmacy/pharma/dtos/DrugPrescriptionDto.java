package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

public class DrugPrescriptionDto {
    private long drugId;
    private int duration;

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
