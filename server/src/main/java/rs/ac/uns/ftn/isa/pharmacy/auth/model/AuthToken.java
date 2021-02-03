package rs.ac.uns.ftn.isa.pharmacy.auth.model;


import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;

public class AuthToken implements IdentityProvider {
    private String email;
    private long userId;
    private String role;

    @Override
    public long getId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
