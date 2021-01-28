package rs.ac.uns.ftn.isa.pharmacy.services.employee;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.dtos.EmployeeDto;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;
    }

    // TODO: Refaktorisi da koristi mapper
    public Set<EmployeeDto> getDermatologistsBasicInfo(long pharmacyId){
        var dermatologists = repository.getDermatologist();
        var dermatologistDtos = new HashSet<EmployeeDto>();
        for (var dermatologist : dermatologists)
            for(var shift : dermatologist.getShifts())
                if(shift.getPharmacy().getId() == pharmacyId)
                    dermatologistDtos.add(new EmployeeDto(dermatologist.getId(),dermatologist.getPerson().getFirstName(),dermatologist.getPerson().getLastName()));

        return dermatologistDtos;
    }
}
