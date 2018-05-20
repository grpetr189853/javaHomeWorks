package petrenko.dao;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import petrenko.util.HibernateUtil;
import petrenko.model.ServiceStation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceStationDAOImpl implements ServiceStationDAO {
    public void addServiceStation(ServiceStation serviceStation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(serviceStation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateServiceStation(ServiceStation serviceStation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(serviceStation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public ServiceStation getServiceStationById(Long serviceStation_id) throws SQLException {
        Session session = null;
        ServiceStation serviceStation = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            serviceStation = (ServiceStation) session.get(ServiceStation.class, serviceStation_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return serviceStation;
    }

    public List<ServiceStation> getAllServiceStations() throws SQLException {
        Session session = null;
        List<ServiceStation> serviceStations = new ArrayList<ServiceStation>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            serviceStations = session.createCriteria(ServiceStation.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return serviceStations;
    }
    public void deleteById(Long serviceStation_id) throws SQLException {
        Session session = null;
        ServiceStation serviceStation = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            serviceStation = (ServiceStation) session.get(ServiceStation.class, serviceStation_id);
            session.beginTransaction();
            session.delete(serviceStation);
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