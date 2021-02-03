package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PatientBasicInfoDto;

public class PatientBasicInfoMapper {
    public static PatientBasicInfoDto objectToDto(Patient patient){
        return new PatientBasicInfoDto(
                patient.getId(),
                patient.getPerson().getPid(),
                patient.getPerson().getFirstName(),
                patient.getPerson().getLastName()
        );
    }
}
