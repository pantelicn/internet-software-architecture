package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ComplaintCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.dto.ResponseCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.service.ComplaintService;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(complaintService.getAllUnanswered());
    }

    @PostMapping
    @Secured(Role.PATIENT)
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody ComplaintCreationDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setPersonId(identityProvider.getPersonId());
        try {
            complaintService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/respond")
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> respond(@RequestBody ResponseCreationDto dto) {
        try {
            complaintService.respond(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
