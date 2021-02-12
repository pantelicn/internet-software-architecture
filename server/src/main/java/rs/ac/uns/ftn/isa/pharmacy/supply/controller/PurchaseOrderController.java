package rs.ac.uns.ftn.isa.pharmacy.supply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.PurchaseOrder;
import rs.ac.uns.ftn.isa.pharmacy.supply.dto.PurchaseOrderMapper;
import rs.ac.uns.ftn.isa.pharmacy.supply.dto.PurchaseOrderOverviewDto;
import rs.ac.uns.ftn.isa.pharmacy.supply.service.PurchaseOrderService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/purchase-order")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purPurchaseOrderService) {
        purchaseOrderService = purPurchaseOrderService;
    }

    @GetMapping
    @Secured(Role.SUPPLIER)
    public ResponseEntity<List<PurchaseOrderOverviewDto>> getAllActive() {
        return ResponseEntity.ok(purchaseOrderService.getAllActive()
                .stream()
                .map(PurchaseOrderMapper::objectToDto)
                .collect(Collectors.toList())
        );
    }
}
