package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.dtos.EmployeeBasicInfoDto;

public class EmployeeBasicInfoMapper {
    public static EmployeeBasicInfoDto objectToDto(Employee employee){
        return new EmployeeBasicInfoDto(employee.getId(),
                employee.getPerson().getFirstName(),
                employee.getPerson().getLastName());
    }
}
