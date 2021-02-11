package rs.ac.uns.ftn.isa.pharmacy.users.admin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.services.AdminService;

@Controller
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> addSystemAdmin(@RequestBody RegistrationDto dto) {
        try {
            adminService.addSystemAdmin(dto);
            return ResponseEntity.ok().build();
        }
        catch (EntityExistsException | EntityNotFoundException | InvalidEntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
