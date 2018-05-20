package petrenko.dao;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import petrenko.model.Mechanic;

import java.sql.SQLException;
import java.util.List;

public interface MechanicDAO {
    void addMechanic(Mechanic mechanic) throws SQLException;
    void updateMechanic(Mechanic mechanic) throws SQLException;
    Mechanic getMechanicById(Long mechanic_id) throws SQLException;
    List<Mechanic> getAllMechanics() throws SQLException;
    void deleteById(Long mechanic_id) throws SQLException;
}