package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

public class RatingDrugDto {
    private long id;
    private String name;
    private double rating;
    private String manufacturer;

    public RatingDrugDto(long id, String name, double rating, String manufacturer) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.manufacturer = manufacturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
