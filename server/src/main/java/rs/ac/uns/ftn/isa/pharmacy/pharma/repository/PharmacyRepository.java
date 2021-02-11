package rs.ac.uns.ftn.isa.pharmacy.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {

    @Query("select p.pharmacyAdmin from Pharmacy p where p.id=:pharmacyId")
    Admin getPharmacyAdmin(@Param("pharmacyId") long pharmacyId);

    @Query(value = "select p from Pharmacy p where p.pharmacyAdmin.id = ?1")
    Pharmacy getByAdmin(long adminId);
}
