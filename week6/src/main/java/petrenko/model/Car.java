package petrenko.model;

/**
 * Created by grpetr189853 on 27.02.2017.
 */

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Почему то при значении AUTO exception : отношение "hibernate_sequence" не существует
    @Column(name = "car_id")
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "car_service_station", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "service_station_id",
                    nullable = false, updatable = false)})
    private Set<ServiceStation> serviceStations = new HashSet();
    @Column(name = "model")
    private String model;
    @Column(name = "make")
    private String make;
    @Column(name = "id_engine")
    private int id_engine;
    @Column(name = "price")
    private int price;
    @Column(name = "date")
    private Date date;
    public Car() {
    }
    public Car(long id,int price, String make, String model){
        this.id=id;
        this.price = price;
        this.make = make;
        this.model = model;
    }
    public Car(int price, String make, String model) {
        this.price = price;
        this.make = make;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public Set<ServiceStation> getServiceStations() {
        return serviceStations;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getId_engine() {
        return id_engine;
    }

    public int getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceStations(Set<ServiceStation> serviceStations) {
        this.serviceStations = serviceStations;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setId_engine(int id_engine) {
        this.id_engine = id_engine;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", serviceStations=" + serviceStations +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", id_engine=" + id_engine +
                ", price=" + price +
                ", date=" + date +
                '}';
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Car other = (Car) o;
        if (!model.equals(other.model))
            return false;
        if (!make.equals(other.make))
            return false;
        if (price != other.price)
            return false;

        return true;
        //return super.equals(o);
    }
}
