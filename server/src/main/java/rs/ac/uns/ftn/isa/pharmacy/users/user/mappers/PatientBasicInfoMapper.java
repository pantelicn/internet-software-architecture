package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Patient;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.PatientBasicInfoDto;

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
