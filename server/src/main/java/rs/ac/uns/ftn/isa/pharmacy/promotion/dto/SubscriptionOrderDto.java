package rs.ac.uns.ftn.isa.pharmacy.promotion.dto;

public class SubscriptionOrderDto {

    private long pharmacyId;
    private long personId;

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
