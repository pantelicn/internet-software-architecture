package rs.ac.uns.ftn.isa.pharmacy.domain.users.user;

public class User {

    private Credentials credentials;
    private PersonalInfo personalInfo;

    public User() {
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
}
