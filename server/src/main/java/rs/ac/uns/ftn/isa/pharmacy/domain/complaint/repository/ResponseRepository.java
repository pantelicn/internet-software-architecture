package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

}
