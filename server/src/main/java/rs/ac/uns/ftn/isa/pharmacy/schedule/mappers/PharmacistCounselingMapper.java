package rs.ac.uns.ftn.isa.pharmacy.schedule.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Shift;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.PharmacistCounselingDto;

public class PharmacistCounselingMapper {
    public static PharmacistCounselingDto objectToDto(Shift shift) {
        return new PharmacistCounselingDto(
                shift.getPharmacy().getId(),
                shift.getPharmacy().getName(),
                shift.getPharmacy().getRating(),
                shift.getPharmacy().getAddress(),
                shift.getPharmacy().getCounselingPrice(),
                shift.getEmployee().getId(),
                shift.getEmployee().getPerson().getFirstName(),
                shift.getEmployee().getPerson().getLastName(),
                shift.getEmployee().getRating()
        );
    }
}
