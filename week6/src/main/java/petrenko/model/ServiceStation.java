package petrenko.model;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "service_stations")
public class ServiceStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_stations_id")
    private Long id;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "car_service_station", joinColumns = {
            @JoinColumn(name = "service_station_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "car_id",
                    nullable = false, updatable = false)})
    private Set<Car> cars = new HashSet();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mechanic_id", nullable = true)
    private Mechanic mechanic;

    public ServiceStation() {
    }

    public ServiceStation(String address, Set<Car> cars, Mechanic mechanic) {
        this.address = address;
        this.cars = cars;
        this.mechanic = mechanic;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    @Override
    public String toString() {
        return "ServiceStation{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", mechanic=" + mechanic +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        ServiceStation other = (ServiceStation) o;
        if (!mechanic.equals(other.mechanic))
            return false;
        if (!cars.equals(other.cars))
            return false;
        if (address != other.address)
            return false;

        return true;
        //return super.equals(o);
    }
}
