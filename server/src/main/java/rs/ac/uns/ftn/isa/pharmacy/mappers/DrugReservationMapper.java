package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.dtos.DrugReservationDto;

public class DrugReservationMapper {
    public static DrugReservation dtoToObject(DrugReservationDto dto) {
        DrugReservation object = new DrugReservation();
        object.setQuantity(dto.getQuantity());
        var storedDrug = new StoredDrug();
        storedDrug.setId(dto.getStoredDrugId());
        object.setStoredDrug(storedDrug);
        object.setPickUpBefore(dto.getPickUpBefore());
        return object;
    }
}
