package rs.ac.uns.ftn.isa.pharmacy.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "select * from offer where supplier_person_id = ?1", nativeQuery = true)
    List<Offer> getBySupplier(long supplierId);

    @Query(value = "select * from offer where purchase_order_id = ?1", nativeQuery = true)
    List<Offer> getByPurchaseOrder(long purchaseOrderId);

    @Query(value = "select * from offer where purchase_order_id = ?1 and supplier_person_id = ?2 limit 1", nativeQuery = true)
    Offer getByPurchaseOrderAndSupplier(long purchaseOrderId, long supplierId);

    @Query(value = "select o from Offer o where o.status = ?1 and o.supplier.id = ?2")
    List<Offer> getByStatusAndSupplier(Offer.Status status, long supplierId);


    // JPQL:
    //      @Query(value = "select o from Offer o where o.supplier.id = ?1")
    // SQL:
    //      @Query(value = "select * from offer where supplier_id = ?1", nativeQuery = true)
}
