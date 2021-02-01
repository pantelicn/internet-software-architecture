package rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "select * from offer where supplier_id = ?1", nativeQuery = true)
    List<Offer> getBySupplierId(long supplierId);

    @Query(value = "select * from offer where purchase_order_id = ?1", nativeQuery = true)
    List<Offer> getByPurchaseOrderId(long purchaseOrderId);


    // JPQL:
    //      @Query(value = "select o from Offer o where o.supplier.id = ?1")
    // SQL:
    //      @Query(value = "select * from offer where supplier_id = ?1", nativeQuery = true)
}
