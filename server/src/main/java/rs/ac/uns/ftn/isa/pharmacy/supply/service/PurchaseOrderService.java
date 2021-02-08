package rs.ac.uns.ftn.isa.pharmacy.supply.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.PurchaseOrder;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.PurchaseOrderRepository;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository orderRepository;

    public PurchaseOrderService(PurchaseOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<PurchaseOrder> getAllActive() {
        return orderRepository.getAllActive();
    }
}

