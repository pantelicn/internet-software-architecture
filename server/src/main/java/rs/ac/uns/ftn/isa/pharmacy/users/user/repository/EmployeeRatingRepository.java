package rs.ac.uns.ftn.isa.pharmacy.users.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.EmployeeRating;

import java.util.Optional;

@Repository
public interface EmployeeRatingRepository extends JpaRepository<EmployeeRating, Long> {
    Optional<EmployeeRating> findByEmployeeIdAndPatientId(long employeeId, long patientId);
}
