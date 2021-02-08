package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;

public class PharmacyAdminCreationDto {

    private RegistrationDto registrationDto;
    private long pharmacyId;

    public RegistrationDto getRegistrationDto() {
        return registrationDto;
    }

    public void setRegistrationDto(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
