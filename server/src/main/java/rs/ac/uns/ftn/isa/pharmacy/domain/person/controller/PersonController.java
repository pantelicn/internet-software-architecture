package rs.ac.uns.ftn.isa.pharmacy.domain.person.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.dto.PersonUpdateDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.service.PersonService;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PutMapping
    @Secured({Role.SUPPLIER, Role.PATIENT})
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody PersonUpdateDto dto) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setPersonId(identityProvider.getUserId());
        try {
            personService.update(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
