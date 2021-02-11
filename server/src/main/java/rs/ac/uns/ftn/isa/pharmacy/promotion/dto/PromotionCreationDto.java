package rs.ac.uns.ftn.isa.pharmacy.promotion.dto;

import java.time.LocalDateTime;

public class PromotionCreationDto {

    private String text;
    private LocalDateTime expirationDate;
    private long pharmacyId;

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}