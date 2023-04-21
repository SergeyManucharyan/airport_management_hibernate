import entity.Companies;
import entity.PassInTrip;
import entity.Passengers;
import entity.Trip;
import jakarta.persistence.*;
import service.CompanyService;
import service.PassengerService;
import service.TripService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        CompanyService cs = new CompanyService();
        PassengerService ps= new PassengerService();
//        ts.update(trip,8882);
//        ps.cancelTrip(1,1181);
//        List<Passengers> passInTrips=ps.getPassengersInTrip(1123);
//        for (Passengers passInTrip : passInTrips) {
//            System.out.println(passInTrip);
//        }
//        Set<Companies> companiesSet=cs.allCompany();
//        for (Companies comp:companiesSet) {
//            System.out.println(comp);
//        }

//        System.out.println(cs.companyById(551));
//        cs.delete(1014);
//        Companies companies = new Companies();
//        companies.setName("Update");
//        companies.setFoundDate(new Date(2023-1900,5,8));
//        cs.update(companies,1012);
//        List<Companies> companiesList = cs.get(5, 10, "foundDate");
//        for (Companies companies : companiesList) {
//            System.out.println(companies);
//        }
    }
}
