package rs.ac.uns.ftn.isa.pharmacy.complaint.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.UnansweredComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.mapper.ComplaintMapper;
import rs.ac.uns.ftn.isa.pharmacy.complaint.dto.ResponseCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.complaint.mapper.ResponseMapper;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Response;
import rs.ac.uns.ftn.isa.pharmacy.complaint.repository.ComplaintRepository;
import rs.ac.uns.ftn.isa.pharmacy.complaint.repository.ResponseRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.schedule.repository.AppointmentRepository;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ResponseRepository responseRepository;
    private final AppointmentRepository appointmentRepository;

    private final ComplaintMapper complaintMapper;
    private final ResponseMapper responseMapper;

    private final EmailService emailService;

    public ComplaintService(
            ComplaintRepository complaintRepository,
            ResponseRepository responseRepository,
            AppointmentRepository appointmentRepository,
            ComplaintMapper complaintMapper,
            ResponseMapper responseMapper,
            EmailService emailService
    ){
        this.complaintRepository = complaintRepository;
        this.responseRepository = responseRepository;
        this.appointmentRepository = appointmentRepository;
        this.complaintMapper = complaintMapper;
        this.responseMapper = responseMapper;
        this.emailService = emailService;
    }

    public void create(ComplaintCreationDto dto) throws MessageException {
        Complaint complaint = complaintMapper.dtoToObject(dto);

        if (complaint.getType() == Complaint.Type.EMPLOYEE_COMPLAINT) {
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

    public void respond(ResponseCreationDto dto) throws MessageException {
        Response response = responseMapper.dtoToObject(dto);

        if (response.getComplaint().isAnswered()) throw new MessageException("This complaint was already answered.");
        response.getComplaint().setAnswered(true);

        Response createdResponse = responseRepository.save(response);
        emailService.sendResponseNoticeMessage(createdResponse);
    }

    public List<UnansweredComplaintDto> getAllUnanswered() {
        return complaintRepository.getAllUnanswered()
                .stream()
                .map(complaintMapper::objectToDto)
                .collect(Collectors.toList());
    }
}
