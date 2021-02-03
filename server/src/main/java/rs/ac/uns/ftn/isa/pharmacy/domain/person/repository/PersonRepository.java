package rs.ac.uns.ftn.isa.pharmacy.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
