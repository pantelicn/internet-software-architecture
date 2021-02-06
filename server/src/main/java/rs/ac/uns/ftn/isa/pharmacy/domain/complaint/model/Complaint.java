package rs.ac.uns.ftn.isa.pharmacy.domain.complaint.model;

import rs.ac.uns.ftn.isa.pharmacy.domain.person.Person;
import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @OneToOne
    private Person author;
    private Complaint.Type type;
    private boolean answered;

    @OneToOne
    private Employee employee;
    @OneToOne
    private Pharmacy pharmacy;

    public enum Type {
        EMPLOYEE_COMPLAINT, PHARMACY_COMPLAINT
    }

    public void validate() throws InvalidEntityException {
        if (text.length() < 10 || text.length() > 200)
            throw new InvalidEntityException("Complaint text");
        if (type == Type.EMPLOYEE_COMPLAINT) {
            if (employee == null || pharmacy != null)
                throw new InvalidEntityException("Complaint");
        }
        if (type == Type.PHARMACY_COMPLAINT) {
            if (employee != null || pharmacy == null)
                throw new InvalidEntityException("Complaint");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
