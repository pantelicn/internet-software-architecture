package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.TimeOffRequest;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.TimeOffRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.TimeOffRequestRepository;

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
