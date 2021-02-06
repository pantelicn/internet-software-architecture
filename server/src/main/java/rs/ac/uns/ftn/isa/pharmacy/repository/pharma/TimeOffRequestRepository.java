package rs.ac.uns.ftn.isa.pharmacy.repository.pharma;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.TimeOffRequest;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest,Long> {
}
