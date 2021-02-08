package rs.ac.uns.ftn.isa.pharmacy.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.TimeOffRequest;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest,Long> {
}
