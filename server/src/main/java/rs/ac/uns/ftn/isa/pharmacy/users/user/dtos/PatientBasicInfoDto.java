package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

public class PatientBasicInfoDto {
    private long id;
    private String pid;
    private String firstName;
    private String lastName;

    public PatientBasicInfoDto(long id, String pid, String firstName, String lastName) {
        this.id = id;
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
