package service;

import entity.Companies;
import entity.Passengers;
import entity.Trip;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TripService {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public Trip getById(int id){
        Trip trip;
        try {
            transaction.begin();
            trip=entityManager.find(Trip.class,id);
            transaction.commit();
        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return trip;
    }
    public Set<Trip> getAll(){
        Set<Trip> tripSet= new LinkedHashSet<>();
        try {
            transaction.begin();
            TypedQuery<Trip> allTrip = entityManager.createNamedQuery("allTrip", Trip.class);
            tripSet.addAll(allTrip.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return tripSet;
    }
    public Set<Trip> get(int offset, int perPage, String sort){
        Set<Trip> tripSet=new LinkedHashSet<>();
        try {
            transaction.begin();
            TypedQuery<Trip> query = entityManager.createQuery("SELECT c FROM Trip c ORDER BY " + sort + " LIMIT ?1 OFFSET ?2",Trip.class);
            query.setParameter(1,perPage);
            query.setParameter(2,offset);
            tripSet.addAll(query.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return tripSet;
    }
    public void save(Trip trip){
        try{
            transaction.begin();
            entityManager.persist(trip);
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void update(Trip trip, int id){
        try{
            transaction.begin();
            Trip trip1 = entityManager.find(Trip.class, id);
            trip1.setAirplane(trip.getAirplane());
            trip1.setCompanyId(trip.getCompanyId());
            trip1.setTimeIn(trip.getTimeIn());
            trip1.setTimeOut(trip.getTimeOut());
            trip1.setTownFrom(trip.getTownFrom());
            trip1.setTownTo(trip.getTownTo());
            entityManager.merge(trip1);

            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void delete(int id){
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(Trip.class,id));
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public List<Trip> getTripsFrom(String city){
        List<Trip> tripList=new LinkedList<>();
        try {
            transaction.begin();
            TypedQuery<Trip> typedQuery=entityManager.createNamedQuery("tripFromCity", Trip.class);
            typedQuery.setParameter(1,city);
            tripList.addAll(typedQuery.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return tripList;
    }
    public List<Trip> getTripsTo(String city){
        List<Trip> tripList=new LinkedList<>();
        try {
            transaction.begin();
            TypedQuery<Trip> typedQuery=entityManager.createNamedQuery("tripToCity", Trip.class);
            typedQuery.setParameter(1,city);
            tripList.addAll(typedQuery.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return tripList;
    }

}
