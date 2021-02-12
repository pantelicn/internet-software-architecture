package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import rs.ac.uns.ftn.isa.pharmacy.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Offer;

import java.time.LocalDateTime;

public class OfferOverviewDto {
    private long id;
    private Money price;
    private LocalDateTime deliveryDeadline;
    private Offer.Status status;
    private PurchaseOrderOverviewDto purchaseOrder;

    public OfferOverviewDto(long id, Money price, LocalDateTime deliveryDeadline, Offer.Status status, PurchaseOrderOverviewDto purchaseOrder) {
        this.id = id;
        this.price = price;
        this.deliveryDeadline = deliveryDeadline;
        this.status = status;
        this.purchaseOrder = purchaseOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public LocalDateTime getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(LocalDateTime deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public Offer.Status getStatus() {
        return status;
    }

    public void setStatus(Offer.Status status) {
        this.status = status;
    }

    public PurchaseOrderOverviewDto getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderOverviewDto purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
