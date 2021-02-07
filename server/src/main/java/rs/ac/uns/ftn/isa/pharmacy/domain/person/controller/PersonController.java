package rs.ac.uns.ftn.isa.pharmacy.domain.person.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.dto.PersonUpdateDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.service.PersonService;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.dtos.CredentialsDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PersonDto;
import rs.ac.uns.ftn.isa.pharmacy.dtos.PersonNameDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PutMapping("/credentials")
    @Secured({Role.SUPPLIER,Role.PATIENT,Role.PHARMACIST,Role.DERMATOLOGIST})
    public ResponseEntity<?> updateCredentials(HttpServletRequest request,
                                               @RequestBody CredentialsDto credentialsDto){
        var identityProvider = HttpRequestUtil.getIdentity(request);

        try{
            personService.updateCredentials(credentialsDto, identityProvider.getUserId());
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/name")
    @Secured({Role.SUPPLIER,Role.PATIENT,Role.PHARMACIST,Role.DERMATOLOGIST})
    public ResponseEntity<?> getFullName(HttpServletRequest request){
        var identityProvider = HttpRequestUtil.getIdentity(request);
        try {
            var personNameDto = new PersonNameDto(personService.get(identityProvider.getUserId()));
            return ResponseEntity.ok(personNameDto);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Secured({Role.SUPPLIER, Role.PATIENT, Role.DERMATOLOGIST, Role.PHARMACIST})
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody PersonUpdateDto dto) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setId(identityProvider.getUserId());
        try {
            personService.update(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    @Secured({Role.PATIENT,Role.DERMATOLOGIST,Role.PHARMACIST})
    public ResponseEntity<?> get (HttpServletRequest request){
        var identityProvider = HttpRequestUtil.getIdentity(request);
        try{
            var person = personService.get(identityProvider.getUserId());
            return ResponseEntity.ok(new PersonDto(person));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
