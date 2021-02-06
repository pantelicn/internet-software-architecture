package rs.ac.uns.ftn.isa.pharmacy.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String> {

    @Query(value = "select * from credentials where uid=:uuid", nativeQuery = true)
    Credentials getByUuid(@Param("uuid") String uuid);
}
