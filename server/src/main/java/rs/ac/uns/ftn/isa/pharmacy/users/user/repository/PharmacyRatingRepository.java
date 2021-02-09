package rs.ac.uns.ftn.isa.pharmacy.users.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.PharmacyRating;

import java.util.Optional;

@Repository
public interface PharmacyRatingRepository extends JpaRepository<PharmacyRating, Long> {
    Optional<PharmacyRating> findByPharmacyIdAndPatientId(long pharmacyId, long patientId);
}
