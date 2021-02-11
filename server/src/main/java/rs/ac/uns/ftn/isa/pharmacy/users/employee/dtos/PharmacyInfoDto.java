package rs.ac.uns.ftn.isa.pharmacy.users.employee.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

public class PharmacyInfoDto {
    private long pharmacyId;
    private String pharmacyName;


    public PharmacyInfoDto(){ }

    public PharmacyInfoDto(Pharmacy pharmacy){
        this.pharmacyId = pharmacy.getId();
        this.pharmacyName = pharmacy.getName();
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
