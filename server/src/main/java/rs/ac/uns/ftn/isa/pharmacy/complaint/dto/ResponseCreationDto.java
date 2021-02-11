package rs.ac.uns.ftn.isa.pharmacy.complaint.dto;

public class ResponseCreationDto {
    private long complaintId;
    private String text;

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
