package rs.ac.uns.ftn.isa.pharmacy.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.SupplierStock;

import java.util.List;

@Repository
public interface SupplierStockRepository  extends JpaRepository<SupplierStock, Long> {

    @Query("select ss from SupplierStock ss where ss.supplier.id = :supplierId")
    List<SupplierStock> getBySupplierId(@Param("supplierId") long supplierId);
}
