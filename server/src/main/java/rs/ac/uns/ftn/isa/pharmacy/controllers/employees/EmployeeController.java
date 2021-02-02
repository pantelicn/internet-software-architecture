package rs.ac.uns.ftn.isa.pharmacy.controllers.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.dtos.EmployeeBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.mappers.EmployeeBasicInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.services.employee.EmployeeService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/dermatologists/basic-info/{pharmacyId}")
    public Set<EmployeeBasicInfoDto> getDermatologistsBasicInfo(@PathVariable int pharmacyId){
        return service.getDermatologistsBasicInfo(pharmacyId)
                .stream()
                .map(EmployeeBasicInfoMapper::objectToDto)
                .collect(Collectors.toSet());
    }
    @GetMapping("/pharmacists/basic-info/{pharmacyId}")
    public Set<EmployeeBasicInfoDto> getPharmacistsBasicInfo(@PathVariable int pharmacyId){
        return service.getPharmacistsBasicInfo(pharmacyId)
                .stream()
                .map(EmployeeBasicInfoMapper::objectToDto)
                .collect(Collectors.toSet());
    }
}
