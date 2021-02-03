package rs.ac.uns.ftn.isa.pharmacy.domain.users.user;

import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Person person;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointment> appointments;
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

    public void penalize() {
        this.penalties += 1;
    }
}
