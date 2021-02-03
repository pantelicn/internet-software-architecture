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

    @GetMapping("supplier/{supplierId}")
    public ResponseEntity<List<Offer>> getBySupplier(@PathVariable long supplierId) {
        return ResponseEntity.ok(offerService.getBySupplierId(supplierId));
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<List<Offer>> getByOrder(@PathVariable long orderId) {
        return ResponseEntity.ok(offerService.getByPurchaseOrderId(orderId));
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

    @GetMapping("status/{status}")
    @Secured(Role.SUPPLIER)
    public ResponseEntity<List<Offer>> getByStatus(
            HttpServletRequest request, @PathVariable Offer.Status status
    ){
        var identityProvider = HttpRequestUtil.getIdentity(request);
        System.out.println(identityProvider.getUserId());
        return ResponseEntity.ok(offerService.getByStatus(status, identityProvider.getUserId()));
    }
}
