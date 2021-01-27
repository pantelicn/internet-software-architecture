package rs.ac.uns.ftn.isa.pharmacy.controllers.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.EmployeeDto;
import rs.ac.uns.ftn.isa.pharmacy.services.employee.EmployeeService;

import java.util.Set;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @GetMapping("/basic-info/{pharmacyId}")
    public Set<EmployeeDto> getEmployeesBasicInfo(@PathVariable int pharmacyId){
        return service.getDermatologist(pharmacyId);
    }
}
