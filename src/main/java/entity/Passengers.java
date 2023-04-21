package entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "allPassenger",query = "SELECT p from Passengers p")
@NamedQuery( name = "passengerById", query = "SELECT p FROM Passengers p WHERE p.id = ?1")
@Table(name = "passengers")
public class Passengers {
//    @OneToMany(mappedBy ="passengers" )
//    private List<PassInTrip> passInTrips;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name="name")
    private String passName;
    @Column(name = "phone")
    private String passPhone;
    @Column(name = "city")
    private String passCity;
    @Column(name="country")
    private String passCountry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public String getPassPhone() {
        return passPhone;
    }

    public void setPassPhone(String passPhone) {
        this.passPhone = passPhone;
    }

    public String getPassCity() {
        return passCity;
    }

    public void setPassCity(String passCity) {
        this.passCity = passCity;
    }

    public String getPassCountry() {
        return passCountry;
    }

    public void setPassCountry(String passCountry) {
        this.passCountry = passCountry;
    }

    @Override
    public String toString() {
        return  id +" " + passName+" "+ passPhone +" " + passCity + " " + passCountry ;
    }
}

