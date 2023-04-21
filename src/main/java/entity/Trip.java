package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedQuery(name= "tripToCity",query = "SELECT t from Trip t where t.townTo=?1")
@NamedQuery(name= "tripFromCity",query = "SELECT t from Trip t where t.townFrom=?1")
@NamedQuery(name = "allTrip",query = "SELECT t from Trip t")
@Table(name = "trip")
public class Trip {
    public Trip() {
    }

    public Trip( Integer companyId, String airplane, String townFrom, String townTo, Timestamp timeOut, Timestamp timeIn) {
        this.companyId = companyId;
        this.airplane = airplane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    //    @OneToMany(mappedBy ="trip" )
//    private List<PassInTrip> passInTrips;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "trip_id")

    private int tripId;
    @Basic
    @Column(name = "company_id")
    private Integer companyId;
    @Basic
    @Column(name = "airplane")
    private String airplane;
    @Basic
    @Column(name = "town_from")
    private String townFrom;
    @Basic
    @Column(name = "town_to")
    private String townTo;
    @Basic
    @Column(name = "time_out")
    private Timestamp timeOut;
    @Basic
    @Column(name = "time_in")
    private Timestamp timeIn;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return tripId +" " + companyId +" " + airplane +
                " "+ townFrom +" "+ townTo +" "+ timeOut +
                " "+ timeIn;
    }
}
