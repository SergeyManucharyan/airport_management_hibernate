package service;

import entity.PassInTrip;
import entity.Passengers;
import entity.Trip;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PassengerService {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public Set<Passengers> allCompany(){
        Set<Passengers> passengers= new LinkedHashSet<>();
        try {
            transaction.begin();
            TypedQuery<Passengers> allCompany = entityManager.createNamedQuery("allPassenger", Passengers.class);
            passengers.addAll(allCompany.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return passengers;
    }
    public Passengers passengerById(int id){
        Passengers passenger;
        try {
            transaction.begin();
            passenger=entityManager.find(Passengers.class,id);
            transaction.commit();
        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return passenger;
    }
    public void savePassenger(Passengers passenger) {
        try {
            transaction.begin();
            entityManager.persist(passenger);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void delete(int id){
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(Passengers.class,id));
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void update(Passengers passenger,int id){
        try {
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE Passengers SET  passName=?1, passPhone=?2, passCity=?3, passCountry=?4  WHERE id=?5");
            query.setParameter(1,passenger.getPassName());
            query.setParameter(2,passenger.getPassPhone());
            query.setParameter(3,passenger.getPassCity());
            query.setParameter(4,passenger.getPassCountry());
            query.setParameter(5,id);
            query.executeUpdate();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public List<Passengers> get(int offset, int perPage, String sort){
        List<Passengers> passengers=new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<Passengers> query = entityManager.createQuery("SELECT c FROM Passengers c ORDER BY " + sort + " LIMIT ?1 OFFSET ?2",Passengers.class);
            query.setParameter(1,perPage);
            query.setParameter(2,offset);
            passengers.addAll(query.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return passengers;
    }
    public List<Passengers> getPassengersInTrip(int id){
        List<PassInTrip>passInTrips=new LinkedList<>();
        List<Passengers> passengers=new LinkedList<>();
        try{
            transaction.begin();
            TypedQuery<PassInTrip> query=entityManager.createQuery("SELECT p FROM PassInTrip p WHERE tripId=?1", PassInTrip.class);
            query.setParameter(1,id);
            passInTrips.addAll(query.getResultList());
            Query queryPass=null;
            for (PassInTrip passInTrip : passInTrips) {
                queryPass = entityManager.createQuery("SELECT p from Passengers p where p.id=?1");
                queryPass.setParameter(1,passInTrip.getPsgId());
                passengers.addAll(queryPass.getResultList());

            }
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return passengers;
    }
    public void registerTrip(Trip trip,Passengers passenger,String place) throws ParseException {
        PassInTrip passInTrip=new PassInTrip();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2007");
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        try{
            transaction.begin();
            passInTrip.setTripId(trip.getTripId());
            passInTrip.setPsgId(passenger.getId());
            passInTrip.setDate(timestamp);
            passInTrip.setPlace(place);
            entityManager.persist(passInTrip);
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
    public void cancelTrip(int passengerId, int tripNumber){
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM PassInTrip p where p.psgId=?1 and p.tripId=?2 ");
            query.setParameter(1,passengerId);
            query.setParameter(2,tripNumber);
            query.executeUpdate();
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }


}

