package rs.ac.uns.ftn.isa.pharmacy.mail.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;

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
