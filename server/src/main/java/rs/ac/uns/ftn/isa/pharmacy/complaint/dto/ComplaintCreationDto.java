package rs.ac.uns.ftn.isa.pharmacy.complaint.dto;

import rs.ac.uns.ftn.isa.pharmacy.complaint.model.Complaint;

public class ComplaintCreationDto {
    private String text;
    private long patientId;
    private long employeeId;
    private long pharmacyId;
    private Complaint.Type type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Complaint.Type getType() {
        return type;
    }

    public void setType(Complaint.Type type) {
        this.type = type;
    }
}
