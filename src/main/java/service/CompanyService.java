package service;

import entity.Companies;
import entity.Trip;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public class CompanyService {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    public Set<Companies> allCompany(){
       Set<Companies> companiesSet= new LinkedHashSet<>();
        try {
            transaction.begin();
            TypedQuery<Companies> allCompany = entityManager.createNamedQuery("allCompany", Companies.class);
            companiesSet.addAll(allCompany.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return companiesSet;
    }
    public Companies companyById(int id){
        Companies companies;
       try {
           transaction.begin();
           companies=entityManager.find(Companies.class,id);
           transaction.commit();
       }finally {
           if (transaction.isActive()) {
               transaction.rollback();
           }
           entityManager.close();
           entityManagerFactory.close();
       }
       return companies;
    }
    public void saveCompany(Companies companies){
        try {
            transaction.begin();
            entityManager.persist(companies);
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
            entityManager.remove((Companies)entityManager.find(Companies.class,id));
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void update(Companies companies,int id){
        try {
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE Companies SET  name=?1, foundDate=?2  WHERE id=?3");
            query.setParameter(1,companies.getName());
            query.setParameter(2,companies.getFoundDate());
            query.setParameter(3,id);
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
    public List<Companies> get(int offset, int perPage, String sort){
        List<Companies> companiesList=new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<Companies> query = entityManager.createQuery("SELECT c FROM Companies c ORDER BY " + sort + " LIMIT ?1 OFFSET ?2", Companies.class);
            query.setParameter(1,perPage);
            query.setParameter(2,offset);
            companiesList.addAll(query.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return companiesList;
    }
}
