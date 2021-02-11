package rs.ac.uns.ftn.isa.pharmacy.locale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.locale.domain.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select c from City c where c.country.id = ?1")
    List<City> findByCountry(long countryId);
}
