package rs.ac.uns.ftn.isa.pharmacy.users.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.DrugRating;

import java.util.Optional;

@Repository
public interface DrugRatingRepository extends JpaRepository<DrugRating, Long> {
    Optional<DrugRating> findByDrugIdAndPatientId(long drugId, long patientId);
}
