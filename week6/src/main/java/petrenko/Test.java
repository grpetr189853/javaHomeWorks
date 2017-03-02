package petrenko;

import petrenko.dao.*;
import petrenko.model.Car;
import petrenko.model.Mechanic;
import petrenko.model.ServiceStation;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
public class Test {
    public static void main(String [] args){
        CarDAO dao=new CarDAOImpl();
        Car car=new Car();
        car.setId(1L);
        car.setModel("BMW");
        car.setPrice(200000);
        Set<Car> carSet=new HashSet<Car>();
        carSet.add(car);
        MechanicDAO mechanicDAO=new MechanicDAOImpl();
        Mechanic mechanic=new Mechanic();
        mechanic.setName("Vasya");

        ServiceStationDAO serviceStationDAO=new ServiceStationDAOImpl();
        ServiceStation serviceStation=new ServiceStation();
        serviceStation.setId(1L);
        serviceStation.setAddress("kdjfkdjfdkjf");

        serviceStation.setCars(carSet);
        serviceStation.setMechanic(mechanic);


        try {
            dao.addCar(car);
            //dao.deleteById(6L);
            mechanicDAO.addMechanic(mechanic);
            serviceStationDAO.addServiceStation(serviceStation);
            List<Car> cars= dao.getAllCars();
            System.out.println("======Все машины========");
            Iterator<Car> iter=cars.iterator();
            while(iter.hasNext()){
                System.out.println(iter.next());
            }
            System.out.println("======Все СТО ==========");
            List<ServiceStation> serviceStations=serviceStationDAO.getAllServiceStations();
            Iterator<ServiceStation> iter1=serviceStations.iterator();
            while(iter1.hasNext()){
                System.out.println(iter1.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
