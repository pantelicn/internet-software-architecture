package rs.ac.uns.ftn.isa.pharmacy.domain.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.admin.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
