package rs.ac.uns.ftn.isa.pharmacy.complaint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

}
