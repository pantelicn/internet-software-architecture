package rs.ac.uns.ftn.isa.pharmacy.complaint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("select com from Complaint com where com.answered = false")
    List<Complaint> getAllUnanswered();

}
