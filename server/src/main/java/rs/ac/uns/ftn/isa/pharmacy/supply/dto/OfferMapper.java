package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Offer;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.PurchaseOrder;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Supplier;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.PurchaseOrderRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.SupplierRepository;

import java.util.Optional;

@Component
public class OfferMapper {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;

    public OfferMapper(PurchaseOrderRepository orderRepository, SupplierRepository supplierRepository) {
        this.purchaseOrderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
    }

    public Offer dtoToObject(OfferRequestDto dto) throws InvalidForeignKeyException {
        Offer offer = new Offer();

        offer.setDeliveryDeadline(dto.getDeliveryDeadline());
        offer.setPrice(dto.getPrice());

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(dto.getPurchaseOrderId());
        Optional<Supplier> supplier = supplierRepository.findById(dto.getSupplierId());

        if (purchaseOrder.isEmpty()) throw new InvalidForeignKeyException("Purchase order");
        if (supplier.isEmpty()) throw new InvalidForeignKeyException("Supplier");

        offer.setSupplier(supplier.get());
        offer.setPurchaseOrder(purchaseOrder.get());

        return offer;
    }

    public static OfferOverviewDto objectToDto(Offer offer) {
        return new OfferOverviewDto(
                offer.getId(),
                offer.getPrice(),
                offer.getDeliveryDeadline(),
                offer.getStatus(),
                PurchaseOrderMapper.objectToDto(offer.getPurchaseOrder())
        );
    }
}
