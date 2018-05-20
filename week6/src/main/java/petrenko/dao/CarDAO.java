package petrenko.dao;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import petrenko.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    void addCar(Car car) throws SQLException;
    void updateCar(Car car) throws SQLException;
    Car getCarById(Long car_id) throws SQLException;
    List<Car> getAllCars() throws SQLException;
    void deleteById(Long car_id) throws SQLException;
}

