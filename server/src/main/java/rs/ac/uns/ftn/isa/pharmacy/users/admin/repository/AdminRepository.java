package rs.ac.uns.ftn.isa.pharmacy.users.admin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
