package rs.ac.uns.ftn.isa.pharmacy.complaint.dto;

import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Complaint;

public class UnansweredComplaintDto {

    private long id;
    private String text;
    private Complaint.Type type;
    private String authorName;

    public UnansweredComplaintDto(long id, String text, Complaint.Type type, String authorName) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.authorName = authorName;
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

    public Complaint.Type getType() {
        return type;
    }

    public void setType(Complaint.Type type) {
        this.type = type;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
