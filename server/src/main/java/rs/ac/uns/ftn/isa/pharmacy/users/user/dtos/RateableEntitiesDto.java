package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import java.util.List;

public class RateableEntitiesDto {
    private List<RatingEmployeeDto> pharmacists;
    private List<RatingEmployeeDto> dermatologists;
    private List<RatingPharmacyDto> pharmacies;
    private List<RatingDrugDto> drugs;

    public RateableEntitiesDto(List<RatingEmployeeDto> pharmacists,
                               List<RatingEmployeeDto> dermatologists,
                               List<RatingPharmacyDto> pharmacies,
                               List<RatingDrugDto> drugs) {
        this.pharmacists = pharmacists;
        this.dermatologists = dermatologists;
        this.pharmacies = pharmacies;
        this.drugs = drugs;
    }

    public List<RatingEmployeeDto> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(List<RatingEmployeeDto> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public List<RatingEmployeeDto> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(List<RatingEmployeeDto> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public List<RatingPharmacyDto> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<RatingPharmacyDto> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<RatingDrugDto> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<RatingDrugDto> drugs) {
        this.drugs = drugs;
    }
}
