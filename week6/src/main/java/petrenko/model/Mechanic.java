package petrenko.model;

/**
 * Created by grpetr189853 on 27.02.2017.
 */
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "mechanic")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanic_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mechanic")
    private Set<ServiceStation> serviceStations=new HashSet();

    public Mechanic() {

    }

    public Mechanic(String name) {
        this.name = name;
    }

    public Mechanic(long id, String name) {
        this.id=id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServiceStation> getServiceStations() {
        return serviceStations;
    }

    public void setServiceStations(Set<ServiceStation> serviceStations) {
        this.serviceStations = serviceStations;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        //return super.equals(o);
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Mechanic other = (Mechanic) o;
        if (!name.equals(other.name))
            return false;

        return true;
    }
}
