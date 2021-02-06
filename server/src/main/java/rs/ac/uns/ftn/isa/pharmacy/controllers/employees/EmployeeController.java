package rs.ac.uns.ftn.isa.pharmacy.controllers.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PharmacyInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.services.employee.EmployeeService;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/my-pharmacies")
    public List<PharmacyInfoDto> getMyPharmacies(HttpServletRequest request){
        IdentityProvider provider = HttpRequestUtil.getIdentity(request);
        return service.getMyPharmacies(provider.getRoleId())
                .stream()
                .map(PharmacyInfoDto::new)
                .collect(Collectors.toList());
    }
}
