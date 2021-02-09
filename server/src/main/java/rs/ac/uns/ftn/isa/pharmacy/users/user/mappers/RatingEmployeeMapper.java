package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingEmployeeDto;

public class RatingEmployeeMapper {
    public static RatingEmployeeDto objectToDto(Employee employee) {
        return new RatingEmployeeDto(
                employee.getId(),
                employee.getPerson().getFirstName(),
                employee.getPerson().getLastName(),
                employee.getRating()
        );
    }
}
