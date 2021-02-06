package rs.ac.uns.ftn.isa.pharmacy.services.employee;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Shift;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;
    }

    public long getEmployeeId(long personId){
        return this.repository.getEmployeeIdByPersonId(personId);
    }

    public List<Pharmacy> getMyPharmacies(long employeeId) {
        var pharmacies = new HashSet<Pharmacy>();
        for (Shift shift : repository.getOne(employeeId).getShifts()) {
            pharmacies.add(shift.getPharmacy());
        }
        return new ArrayList<>(pharmacies);
    }
}
