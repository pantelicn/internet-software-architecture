package rs.ac.uns.ftn.isa.pharmacy.users.employee.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Shift;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;

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

    public Employee findByPersonId(long id) {
        return repository.findByPersonId(id);
    }
}
