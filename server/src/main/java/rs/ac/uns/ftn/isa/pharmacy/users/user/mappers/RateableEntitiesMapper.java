package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RateableEntitiesDto;

import java.util.List;
import java.util.stream.Collectors;

public class RateableEntitiesMapper {
    public static RateableEntitiesDto objectsToDto(List<Employee> pharmacists,
                                                   List<Employee> dermatologists,
                                                   List<Drug> drugs,
                                                   List<Pharmacy> pharmacies) {
        var pharmacistDtos = pharmacists.stream()
                .map(RatingEmployeeMapper::objectToDto).collect(Collectors.toList());
        var dermatologistDtos = dermatologists.stream()
                .map(RatingEmployeeMapper::objectToDto).collect(Collectors.toList());
        var drugDtos = drugs.stream()
                .map(RatingDrugMapper::objectToDto).collect(Collectors.toList());
        var pharmacyDtos = pharmacies.stream()
                .map(RatingPharmacyMapper::objectToDto).collect(Collectors.toList());
        return new RateableEntitiesDto(pharmacistDtos, dermatologistDtos, pharmacyDtos, drugDtos);
    }
}
