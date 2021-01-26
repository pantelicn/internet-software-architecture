package rs.ac.uns.ftn.isa.pharmacy.domain.users.employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date start;
    private Date end;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }

}
