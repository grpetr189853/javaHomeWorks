package petrenko.dao;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import petrenko.util.HibernateUtil;
import petrenko.model.Car;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    public void addCar(Car car) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateCar(Car car) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public Car getCarById(Long car_id) throws SQLException {
        Session session = null;
        Car car = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            car = (Car) session.get(Car.class, car_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return car;
    }

    public List<Car> getAllCars() throws SQLException {
        Session session = null;
        List<Car> cars = new ArrayList<Car>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cars = session.createCriteria(Car.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cars;
    }

    public void deleteById(Long car_id) throws SQLException {
        Session session = null;
        Car car = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            car = (Car) session.get(Car.class, car_id);
            session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}