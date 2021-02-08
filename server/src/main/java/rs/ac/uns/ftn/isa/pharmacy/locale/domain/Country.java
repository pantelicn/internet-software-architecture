package rs.ac.uns.ftn.isa.pharmacy.locale.domain;

import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;

    public void validate() throws InvalidEntityException {
        if (name == null) throw new InvalidEntityException("Country name");
        if (code == null) throw new InvalidEntityException("Country code");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }
}
