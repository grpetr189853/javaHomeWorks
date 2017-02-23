package petrenko;

import java.sql.*;
import java.util.*;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.slf4j.*;
/**
 * Created by grpetr189853 on 20.02.2017.
 */
public class Engine {
    private int id;
    private int displacement;
    private int power;
    private Set<Car> carSet;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Engine.class.getName());
    private static List<Engine> engineList=new ArrayList<Engine>();
    public Engine(int id,int displacement,int power,Set<Car> carSet){
        this.id=id;
        this.displacement=displacement;
        this.power=power;
        this.carSet=carSet;
    }
    public Engine(int displacement,int power){
        this.displacement=displacement;
        this.power=power;
    }
    public int getId() {
        return id;
    }

    public int getDisplacement() {
        return displacement;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", displacement=" + displacement +
                ", power=" + power +
                ", carSet=" + carSet +
                '}';
    }
    public static void logger(ResultSet result) throws SQLException {
        /*
            * result is a pointer to the first line with sample
            * to get the data, we will use
            * method next (), by which we pass on to the next element
            */

        LOGGER.info("Print preparedStatement " + result.getString("name"));
        while (result.next()) {
            LOGGER.info("The selection number #" + result.getRow()
                    + "\t number in the database #" + result.getInt("test_id")
                    + "\t" + result.getString("name")
                    + "\t the number in the database #" + result.getInt("count"));
        }
    }

    public static Engine getEngineById(int id){
        String URL="jdbc:postgresql://localhost:5432/cars";
        Engine engine=null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(URL, "postgres", "root");
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            ResultSet rs = st1.executeQuery("Select id,displacement,power from CARS.PUBLIC.ENGINE");
            ResultSet rsCars=st.executeQuery("Select id,model,make,price,id_engine from CARS.PUBLIC.CAR where id_engine="+id);
            Set<Car> carSet=new HashSet<Car>();
            while(rsCars.next()) {
                Car car = new Car(rsCars.getInt("id"), rsCars.getString("model"), rsCars.getDate("make"), rsCars.getInt("price"), rsCars.getInt("id_engine"));
                carSet.add(car);
            }
            while(rs.next()){
                engine=new Engine(rs.getInt("id"),rs.getInt("displacement"),rs.getInt("power"),carSet);
                engineList.add(engine);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator<Engine> iter=engineList.iterator();
        while(iter.hasNext()){
            Engine eng=iter.next();
            if (eng.getId()==id){
                engine=eng;
            }
        }
        return engine;
    }
    public static void insertEngine(Engine engine) throws SQLException{
        String URL="jdbc:postgresql://localhost:5432/cars";

        Connection con = DriverManager.getConnection(URL, "postgres", "root");
        try {

            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO ENGINE (displacement,power) VALUES (?,?)");
            preparedStatement.setInt(1,engine.getDisplacement());
            preparedStatement.setInt(2,engine.getPower());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    LOGGER.error("Close connection is failed ", e.getMessage(), e);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(Engine.getEngineById(5));
        Scanner read=new Scanner(System.in);
        System.out.println("Введите литраж нового двигателя");
        int displacement= read.nextInt();
        System.out.println("Введите мощность нового двигателя");
        int power = read.nextInt();
        Engine engine=new Engine(displacement,power);
        try {
            Engine.insertEngine(engine);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
