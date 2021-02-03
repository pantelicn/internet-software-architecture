package rs.ac.uns.ftn.isa.pharmacy.domain.supply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.dto.OfferRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.service.OfferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    @Secured(Role.SUPPLIER)
    public ResponseEntity<List<Offer>> getBySupplier(HttpServletRequest request) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        return ResponseEntity.ok(offerService.getBySupplierId(identityProvider.getUserId()));
    }

    @GetMapping("order/{orderId}")
    @Secured(Role.PH_ADMIN)
    public ResponseEntity<List<Offer>> getByOrder(@PathVariable long orderId) {
        // TODO Who can see all offers for one PO? PH_ADMIN or someone else?
        // Also, should we block other PH_ADMINs from viewing each other's offers?
        return ResponseEntity.ok(offerService.getByPurchaseOrderId(orderId));
    }

    @GetMapping("status/{status}")
    @Secured(Role.SUPPLIER)
    public ResponseEntity<List<Offer>> getByStatus(
            HttpServletRequest request, @PathVariable Offer.Status status
    ){
        var identityProvider = HttpRequestUtil.getIdentity(request);
        return ResponseEntity.ok(offerService.getByStatus(status, identityProvider.getUserId()));
    }

    @PostMapping
    @Secured(Role.SUPPLIER)
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody OfferRequestDto dto) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setSupplierId(identityProvider.getUserId());
        try {
            offerService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Secured(Role.SUPPLIER)
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody OfferRequestDto dto) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        dto.setSupplierId(identityProvider.getUserId());
        try {
            offerService.update(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
