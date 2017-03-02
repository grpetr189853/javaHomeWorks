package petrenko.dao;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import petrenko.model.Car;
import petrenko.model.ServiceStation;

import java.sql.SQLException;
import java.util.List;

public interface ServiceStationDAO {
    void addServiceStation(ServiceStation serviceStation) throws SQLException;
    void updateServiceStation(ServiceStation serviceStation) throws SQLException;
    ServiceStation getServiceStationById(Long serviceStation_id) throws SQLException;
    List<ServiceStation> getAllServiceStations() throws SQLException;
    void deleteById(Long serviceStation_id) throws SQLException;
}