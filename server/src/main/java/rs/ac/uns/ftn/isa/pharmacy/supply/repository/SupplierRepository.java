package rs.ac.uns.ftn.isa.pharmacy.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
