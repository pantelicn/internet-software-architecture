package rs.ac.uns.ftn.isa.pharmacy.domain.supply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
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
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody OfferRequestDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        if (identityProvider == null)
            return ResponseEntity.status(401).build();
        dto.setSupplierId(identityProvider.getId());
        try {
            offerService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody OfferRequestDto dto) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        if (identityProvider == null)
            return ResponseEntity.status(401).build();
        dto.setSupplierId(identityProvider.getId());
        try {
            offerService.update(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("status/{status}")
    public ResponseEntity<List<Offer>> getByStatus(
            HttpServletRequest request, @PathVariable Offer.Status status
    ){
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        if (identityProvider == null)
            return ResponseEntity.status(401).build();
        return ResponseEntity.ok(offerService.getByStatus(status, identityProvider.getId()));
    }
}
