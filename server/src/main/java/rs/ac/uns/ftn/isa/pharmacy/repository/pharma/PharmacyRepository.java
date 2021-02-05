package rs.ac.uns.ftn.isa.pharmacy.repository.pharma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.admin.Admin;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {

    @Query("select p.pharmacyAdmin from Pharmacy p where p.id=:pharmacyId")
    Admin getPharmacyAdmin(@Param("pharmacyId") long pharmacyId);
}
