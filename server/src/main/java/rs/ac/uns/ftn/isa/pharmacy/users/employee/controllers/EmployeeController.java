package rs.ac.uns.ftn.isa.pharmacy.users.employee.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.dtos.PharmacyInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("register-dermatologist")
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> create(@RequestBody RegistrationDto dto) {
        try {
            employeeService.createDermatologist(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/employee-id/{personId}")
    public long getEmployeeId(@PathVariable long personId){
        return employeeService.getEmployeeId(personId);
    }

    @Secured({Role.DERMATOLOGIST,Role.PHARMACIST})
    @GetMapping("/my-pharmacies")
    public List<PharmacyInfoDto> getMyPharmacies(HttpServletRequest request){
        IdentityProvider provider = HttpRequestUtil.getIdentity(request);
        return employeeService.getMyPharmacies(provider.getRoleId())
                .stream()
                .map(PharmacyInfoDto::new)
                .collect(Collectors.toList());
    }
}
