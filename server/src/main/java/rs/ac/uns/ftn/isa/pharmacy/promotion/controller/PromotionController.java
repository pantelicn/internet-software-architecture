package rs.ac.uns.ftn.isa.pharmacy.promotion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.promotion.dto.PromotionCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.promotion.dto.SubscriptionOrderDto;
import rs.ac.uns.ftn.isa.pharmacy.promotion.service.PromotionService;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    @Secured(Role.PH_ADMIN)
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody PromotionCreationDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setPharmacyId(identityProvider.getRoleId());
        try {
            promotionService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (InvalidEntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("subscribe")
    @Secured(Role.PATIENT)
    public ResponseEntity<?> subscribe(HttpServletRequest request, @RequestBody SubscriptionOrderDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setPersonId(identityProvider.getPersonId());
        try {
            promotionService.subscribe(dto);
            return ResponseEntity.ok().build();
        }
        catch (EntityAlreadyExistsException | InvalidForeignKeyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("unsubscribe")
    @Secured(Role.PATIENT)
    public ResponseEntity<?> unsubscribe(HttpServletRequest request, @RequestBody SubscriptionOrderDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setPersonId(identityProvider.getPersonId());
        try {
            promotionService.unsubscribe(dto);
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
