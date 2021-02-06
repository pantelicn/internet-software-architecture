package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ComplaintMapper;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.repository.ComplaintRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.repository.schedule.AppointmentRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final AppointmentRepository appointmentRepository;

    private final ComplaintMapper complaintMapper;

    public ComplaintService(
            ComplaintRepository complaintRepository,
            AppointmentRepository appointmentRepository,
            ComplaintMapper complaintMapper
    ){
        this.complaintRepository = complaintRepository;
        this.appointmentRepository = appointmentRepository;
        this.complaintMapper = complaintMapper;
    }

    public void create(ComplaintCreationDto dto) throws MessageException {
        Complaint complaint = complaintMapper.dtoToObject(dto);

        if (complaint.getType() == Complaint.Type.EMPLOYEE_COMPLAINT) {
            System.out.println("Looking for PE: " + complaint.getAuthor().getId() + " " + complaint.getEmployee().getId());
            if (appointmentRepository.countByPatientAndEmployee(
                    complaint.getEmployee().getId(), complaint.getAuthor().getId()) == 0)
                throw new MessageException("You have not yet had appointments with this employee.");
        }
        else {
            if (appointmentRepository.countByPatientAndPharmacy(
                    complaint.getPharmacy().getId(), complaint.getAuthor().getId()) == 0)
                throw new MessageException("You have not yet had experience with this pharmacy.");
        }

        complaintRepository.save(complaint);
    }
}
