package rs.ac.uns.ftn.isa.pharmacy.services.employee;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.dtos.EmployeeBasicInfoDto;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;
    }

    public Set<Employee> getDermatologistsBasicInfo(long pharmacyId){
        return filterEmployees(pharmacyId, repository.getDermatologist());
    }
    public Set<Employee> getPharmacistsBasicInfo(long pharmacyId){
        return filterEmployees(pharmacyId, repository.getPharmacist());
    }

    private Set<Employee> filterEmployees(long pharmacyId, List<Employee> dermatologists) {
        var filteredDermatologists = new HashSet<Employee>();
        for (var dermatologist : dermatologists)
            for (var shift : dermatologist.getShifts())
                if (shift.getPharmacy().getId() == pharmacyId)
                    filteredDermatologists.add(dermatologist);
        return filteredDermatologists;
    }
}
