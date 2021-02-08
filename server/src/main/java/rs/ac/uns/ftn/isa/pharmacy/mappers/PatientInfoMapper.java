package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientInfoDto;

public class PatientInfoMapper {
    public static PatientInfoDto objectToDto(Patient patient) {
        return new PatientInfoDto(
                patient.getId(),
                patient.getPenalties(),
                patient.getAllergicTo()
        );
    }
}
