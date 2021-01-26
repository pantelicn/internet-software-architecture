package rs.ac.uns.ftn.isa.pharmacy.domain.schedule;

import rs.ac.uns.ftn.isa.pharmacy.domain.finance.Money;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Shift;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Term;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Person;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    private long id;
    @Embedded
    private Term term;
    private AppointmentType type;
    @Embedded
    private Money price;
    @ManyToOne
    private Person patient;
    @ManyToOne(optional = false)
    private Shift shift;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
