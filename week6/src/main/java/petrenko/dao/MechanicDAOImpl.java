package petrenko.dao;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import petrenko.model.Mechanic;
import petrenko.util.HibernateUtil;
import petrenko.model.Car;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanicDAOImpl implements MechanicDAO {
    public void addMechanic(Mechanic mechanic) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(mechanic);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateMechanic(Mechanic mechanic) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(mechanic);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public Mechanic getMechanicById(Long mechanic_id) throws SQLException {
        Session session = null;
        Mechanic mechanic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            mechanic = (Mechanic) session.get(Mechanic.class, mechanic_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return mechanic;
    }

    public List<Mechanic> getAllMechanics() throws SQLException {
        Session session = null;
        List<Mechanic> mechanics = new ArrayList<Mechanic>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            mechanics = session.createCriteria(Mechanic.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return mechanics;
    }
    public void deleteById(Long mechanic_id) throws SQLException {
        Session session = null;
        Mechanic mechanic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            mechanic = (Mechanic) session.get(Mechanic.class, mechanic_id);
            session.beginTransaction();
            session.delete(mechanic);
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

