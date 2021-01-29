package rs.ac.uns.ftn.isa.pharmacy.domain.pharma;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prescription", uniqueConstraints = {})
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long personId;
    private LocalDate issueDate;
    @OneToMany
    List<DrugPrescribed> drugs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }


}
