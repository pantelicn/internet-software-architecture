package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Complaint;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.service.ComplaintService;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    @Secured(Role.PATIENT)
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody ComplaintCreationDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setPersonId(identityProvider.getUserId());
        try {
            complaintService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
