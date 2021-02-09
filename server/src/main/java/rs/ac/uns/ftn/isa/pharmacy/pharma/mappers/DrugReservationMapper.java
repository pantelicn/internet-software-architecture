package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.DrugReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredDrug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.DrugReservationDto;

public class DrugReservationMapper {
    public static DrugReservation dtoToObject(DrugReservationDto dto) {
        DrugReservation object = new DrugReservation();
        object.setDispensed(false);
        object.setQuantity(dto.getQuantity());
        var storedDrug = new StoredDrug();
        storedDrug.setId(dto.getStoredDrugId());
        object.setStoredDrug(storedDrug);
        object.setPickUpBefore(dto.getPickUpBefore());
        return object;
    }

    public static DrugReservationDto objectToDto(DrugReservation reservation) {
        return new DrugReservationDto(
                reservation.getId(),
                reservation.getStoredDrug().getDrug().getName(),
                reservation.getStoredDrug().getDrug().getManufacturer(),
                reservation.getStoredDrug().getPharmacy().getName(),
                reservation.getStoredDrug().getPrice(),
                reservation.getPickUpBefore(),
                reservation.getQuantity()
        );
    }
}
