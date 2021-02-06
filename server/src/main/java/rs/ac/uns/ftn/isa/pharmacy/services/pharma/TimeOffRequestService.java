package rs.ac.uns.ftn.isa.pharmacy.services.pharma;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.TimeOffRequest;
import rs.ac.uns.ftn.isa.pharmacy.dtos.TimeOffRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.repository.employee.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.repository.pharma.TimeOffRequestRepository;

@Service
public class TimeOffRequestService {
    private final EmployeeRepository employeeRepository;
    private final PharmacyRepository pharmacyRepository;
    private final TimeOffRequestRepository timeOffRequestRepository;

    public TimeOffRequestService(EmployeeRepository employeeRepository, PharmacyRepository pharmacyRepository, TimeOffRequestRepository timeOffRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.timeOffRequestRepository = timeOffRequestRepository;
    }

    public void submit(TimeOffRequestDto timeOffRequestDto){
        var timeOfRequest = new TimeOffRequest(
                pharmacyRepository.getOne(timeOffRequestDto.getPharmacyId()),
                employeeRepository.getOne(timeOffRequestDto.getEmployeeId()),
                timeOffRequestDto.getRequestInfo(),
                timeOffRequestDto.getStart(),
                timeOffRequestDto.getEnd()
        );
        timeOffRequestRepository.save(timeOfRequest);

    }


}
