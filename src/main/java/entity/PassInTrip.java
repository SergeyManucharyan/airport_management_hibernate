package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "pass_in_trip", schema = "public", catalog = "Airport_Management")
public class PassInTrip {

//    @ManyToOne
//    @JoinColumn(name="psg_id")
//    private Passengers passengers;
//    @ManyToOne
//    @JoinColumn(name = "trip_id" )
//    private Trip trip;
    @Column(name = "trip_id")
    private int tripId;
    @Basic
    @Column(name = "psg_id")
    private int psgId;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @Basic
    @Column(name = "place")
    private String place;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pass_in_trip_id")
    private int passInTripId;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Integer getPsgId() {
        return psgId;
    }

    public void setPsgId(Integer psgId) {
        this.psgId = psgId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPassInTripId() {
        return passInTripId;
    }

    public void setPassInTripId(int passInTripId) {
        this.passInTripId = passInTripId;
    }


//    public Trip getTrip() {
//        return trip;
//    }
//
//    public void setTrip(Trip trip) {
//        this.trip = trip;
//    }

    @Override
    public String toString() {
        return   tripId +" " + psgId +" "+ date +" "+ place + " "+ passInTripId ;
    }
}
