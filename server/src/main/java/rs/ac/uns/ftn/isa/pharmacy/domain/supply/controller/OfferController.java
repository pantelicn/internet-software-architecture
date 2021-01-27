package rs.ac.uns.ftn.isa.pharmacy.domain.supply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository.OfferRepository;

import java.util.List;

@Controller
@RequestMapping("api/offer")
public class OfferController {

    private final OfferRepository offerRepository;

    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping("supplier")
    public ResponseEntity<List<?>> getBySupplier(@PathVariable long supplierId) {
        return ResponseEntity.ok(offerRepository.getBySupplierId(supplierId));
    }

    @GetMapping("order")
    public ResponseEntity<List<?>> getByOrder(@PathVariable long orderId) {
        return ResponseEntity.ok(offerRepository.getByPurchaseOrderId(orderId));
    }
}
