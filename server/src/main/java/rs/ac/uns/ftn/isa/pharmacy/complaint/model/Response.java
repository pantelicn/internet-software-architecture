package rs.ac.uns.ftn.isa.pharmacy.complaint.model;

import javax.persistence.*;

@Entity
@Table(name = "complaint_responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @OneToOne
    private Complaint complaint;

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

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
