package rs.ac.uns.ftn.isa.pharmacy.domain.supply.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.PurchaseOrder;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository.PurchaseOrderRepository;

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

