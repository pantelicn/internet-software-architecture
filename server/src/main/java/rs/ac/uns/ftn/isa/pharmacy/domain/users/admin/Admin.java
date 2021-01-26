package rs.ac.uns.ftn.isa.pharmacy.domain.users.admin;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Person;

public class Admin extends Person {
    private AdminType adminType;

    public AdminType getAdminType() {
        return adminType;
    }

    public void setAdminType(AdminType adminType) {
        this.adminType = adminType;
    }
}
