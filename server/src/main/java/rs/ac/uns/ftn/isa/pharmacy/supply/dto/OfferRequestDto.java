package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.PurchaseOrder;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Supplier;

import java.time.LocalDateTime;

/**
 * Dto sent by <see>{@link Supplier}</see> to apply for a <see>{@link PurchaseOrder}</see>
 */
public class OfferRequestDto {
    private LocalDateTime deliveryDeadline;
    private Money price;
    private long purchaseOrderId;
    private long supplierId;

    public LocalDateTime getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(LocalDateTime deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
