package entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@NamedQuery(name = "allCompany",query = "SELECT c from Companies c")
@NamedQuery( name = "companyById", query = "SELECT c FROM Companies c WHERE c.id = ?1")
@Table(name="companies")
public class Companies {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "found_date")
    private Date foundDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Companies companies = (Companies) o;

        if (id != companies.id) return false;
        if (name != null ? !name.equals(companies.name) : companies.name != null) return false;
        if (foundDate != null ? !foundDate.equals(companies.foundDate) : companies.foundDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (foundDate != null ? foundDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id+" "+name+" "+foundDate;
    }
}
