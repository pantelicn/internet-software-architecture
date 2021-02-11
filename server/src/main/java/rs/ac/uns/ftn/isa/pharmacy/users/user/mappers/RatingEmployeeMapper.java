package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.EmployeeRating;
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

    public static EmployeeRating dtoToObject(RatingEmployeeDto dto) {
        var rating = new EmployeeRating();
        rating.setEmployee(new Employee());
        rating.getEmployee().setId(dto.getEmployeeId());
        rating.setRating(dto.getRating());
        return rating;
    }
}
