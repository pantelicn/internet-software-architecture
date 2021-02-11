package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.PatientInfoDto;

public class PatientInfoMapper {
    public static PatientInfoDto objectToDto(Patient patient) {
        return new PatientInfoDto(
                patient.getId(),
                patient.getPenalties(),
                patient.getAllergicTo()
        );
    }
}
