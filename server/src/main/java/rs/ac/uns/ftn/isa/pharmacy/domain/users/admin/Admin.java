package rs.ac.uns.ftn.isa.pharmacy.domain.users.admin;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.User;

public class Admin extends User {
    private AdminType adminType;

    public Admin() {
    }

    public AdminType getAdminType() {
        return adminType;
    }

    public void setAdminType(AdminType adminType) {
        this.adminType = adminType;
    }
}
