package rs.ac.uns.ftn.isa.pharmacy.controllers.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PharmacyInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.services.employee.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employee-id/{personId}")
    public long getEmployeeId(@PathVariable long personId){
        return service.getEmployeeId(personId);
    }

    @GetMapping("/my-pharmacies/{employeeId}")
    public List<PharmacyInfoDto> getMyPharmacies(@PathVariable long employeeId){
        return service.getMyPharmacies(employeeId)
                .stream()
                .map(PharmacyInfoDto::new)
                .collect(Collectors.toList());
    }


}
