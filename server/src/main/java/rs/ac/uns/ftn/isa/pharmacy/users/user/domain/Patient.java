package rs.ac.uns.ftn.isa.pharmacy.users.user.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patients")
@SequenceGenerator(name = "patients_seq", initialValue = 100, allocationSize = 1)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_seq")
    private long id;
    @OneToOne
    private Person person;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointment> appointments;
    @ManyToMany
    private List<Drug> allergicTo;
    private int penalties;

    public Boolean canSchedule(Appointment appointment){
        for(var patientAppointment:appointments)
            if(appointment.overlaps(patientAppointment.getTerm()))
                return false;
        return true;
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointment) {
        this.appointments = appointment;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public List<Drug> getAllergicTo() {
        return allergicTo;
    }

    public void setAllergicTo(List<Drug> allergicTo) {
        this.allergicTo = allergicTo;
    }

    public void penalize() {
        this.penalties += 1;
    }

    public boolean isBanned() {
        return this.penalties >= 3;
    }
}
