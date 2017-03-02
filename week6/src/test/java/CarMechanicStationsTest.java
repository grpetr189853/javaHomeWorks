/**
 * Created by grpetr189853 on 27.02.2017.
 */
import petrenko.model.*;
import petrenko.dao.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
public class CarMechanicStationsTest {
    @org.junit.Test
    public void testCarInsert() {
        CarDAO carDAO = new CarDAOImpl();
        long id = 50L;
        Car car = new Car(id, 150000, "Audi", "good");
        try {
            carDAO.addCar(car);
            Car car1=carDAO.getCarById(id);
            assertEquals(car,car1);
            carDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void testServiceStationInsert() {
        ServiceStationDAO serviceStationDAO = new ServiceStationDAOImpl();
        Car car=new Car();
        car.setModel("BMW");
        car.setPrice(200000);
        Set<Car> carSet=new HashSet<Car>();
        carSet.add(car);
        Mechanic mechanic = new Mechanic("Vasya");
        long id = 24L;
        ServiceStation serviceStation = new ServiceStation("Poltava",carSet,mechanic);
        try {
            serviceStationDAO.addServiceStation(serviceStation);
            ServiceStation serviceStation1=serviceStationDAO.getServiceStationById(id);
            assertEquals(serviceStation,serviceStation1);
            serviceStationDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void testMechanicInsert() {
        MechanicDAO mechanicDAO = new MechanicDAOImpl();
        long id = 18L;
        Mechanic mechanic = new Mechanic(id,"Vasya");
        try {
            mechanicDAO.addMechanic(mechanic);
            Mechanic mechanic1=mechanicDAO.getMechanicById(id);
            assertEquals(mechanic,mechanic1);
            mechanicDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
