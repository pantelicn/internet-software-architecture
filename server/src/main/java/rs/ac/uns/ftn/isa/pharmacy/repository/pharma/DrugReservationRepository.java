package rs.ac.uns.ftn.isa.pharmacy.repository.pharma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.DrugReservation;

@Repository
public interface DrugReservationRepository extends JpaRepository<DrugReservation, Long> {
}
