package rs.ac.uns.ftn.isa.pharmacy.domain.supply;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
