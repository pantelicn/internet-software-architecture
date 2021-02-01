package rs.ac.uns.ftn.isa.pharmacy.domain.supply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.dto.OfferMapper;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.dto.OfferRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InsufficientDrugAmountException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.LateDeadlineException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.service.OfferService;

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
    public ResponseEntity<?> create(OfferRequestDto dto) {
        try {
            offerService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
