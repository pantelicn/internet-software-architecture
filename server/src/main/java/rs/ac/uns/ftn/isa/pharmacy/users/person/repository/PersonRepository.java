package rs.ac.uns.ftn.isa.pharmacy.users.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
