package rs.ac.uns.ftn.isa.pharmacy.domain.users.employee;

import java.util.Date;

public class Shift {
    private Date start;
    private Date end;

    public Shift() {
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
