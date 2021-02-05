package rs.ac.uns.ftn.isa.pharmacy.domain.mail.messages.dtos;

import rs.ac.uns.ftn.isa.pharmacy.domain.pharma.Drug;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.admin.Admin;

public class DrugNonExistentMailInfo {
    private String drugName;
    private String adminMail;
    private String adminFullName;

    public DrugNonExistentMailInfo(Drug drug, Admin admin){
        this.drugName = drug.getName();
        this.adminMail = admin.getPerson().getCredentials().getEmail();
        this.adminFullName = admin.getPerson().getFirstName() + " " + admin.getPerson().getLastName();
    }

    public String getDrugName() {
        return drugName;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public String getAdminFullName() {
        return adminFullName;
    }
}
