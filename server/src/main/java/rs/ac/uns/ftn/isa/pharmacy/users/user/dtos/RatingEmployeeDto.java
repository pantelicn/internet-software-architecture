package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

public class RatingEmployeeDto {
    private long employeeId;
    private String name;
    private String lastName;
    private double rating;

    public RatingEmployeeDto(long employeeId, String name, String lastName, double rating) {
        this.employeeId = employeeId;
        this.name = name;
        this.lastName = lastName;
        this.rating = rating;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
