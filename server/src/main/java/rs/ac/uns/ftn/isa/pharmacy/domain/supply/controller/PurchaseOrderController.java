package rs.ac.uns.ftn.isa.pharmacy.domain.supply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.PurchaseOrder;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.service.PurchaseOrderService;

import java.util.List;

@Controller
@RequestMapping("api/purchase-order")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purPurchaseOrderService) {
        purchaseOrderService = purPurchaseOrderService;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllActive() {
        return ResponseEntity.ok(purchaseOrderService.getAllActive());
    }
}
