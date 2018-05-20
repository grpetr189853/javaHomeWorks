package petrenko.dao;

/**
 * Created by grpetr189853 on 09.03.2017.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import petrenko.model.User;
import petrenko.util.HibernateUtil;
public interface UserDao {
    void addUser(User user) throws SQLException;
    void deleteUser(int userId) throws SQLException;
    void updateUser(User user) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    User getUserById(int userId) throws SQLException;
}
