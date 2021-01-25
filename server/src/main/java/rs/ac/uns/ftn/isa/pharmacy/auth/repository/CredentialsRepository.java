package rs.ac.uns.ftn.isa.pharmacy.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String> {

}
