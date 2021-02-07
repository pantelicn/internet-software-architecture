package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ResponseCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.UnansweredComplaintDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Response;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.repository.ComplaintRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;

import java.util.Optional;

@Component
public class ResponseMapper {

    private final ComplaintRepository complaintRepository;

    public ResponseMapper(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Response dtoToObject(ResponseCreationDto dto) throws InvalidForeignKeyException {
        Response response = new Response();

        response.setText(dto.getText());
        Optional<Complaint> optionalComplaint = complaintRepository.findById(dto.getComplaintId());
        if (optionalComplaint.isEmpty())
            throw new InvalidForeignKeyException("Complaint");
        response.setComplaint(optionalComplaint.get());

        return response;
    }
}
