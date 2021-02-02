package rs.ac.uns.ftn.isa.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
