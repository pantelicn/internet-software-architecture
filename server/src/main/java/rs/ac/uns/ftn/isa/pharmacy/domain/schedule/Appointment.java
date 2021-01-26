package rs.ac.uns.ftn.isa.pharmacy.domain.schedule;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Term;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Person;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table("appointment")
public class Appointment {
    @Id
    private long id;
    @Embedded
    private Term term;
    private AppointmentType type;
    private Money price;
    @ManyToOne
    private Person patient;
}
