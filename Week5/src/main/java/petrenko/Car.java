package petrenko;


//import org.apache.log4j.spi.LoggerFactory;

//import org.apache.log4j.spi.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;
import org.slf4j.*;


/**
 * Created by grpetr189853 on 20.02.2017.
 */
public class Car {
    private int id;
    private String model;
    private Date make;
    private int price;
    private int engine;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Car.class.getName());
    public int getId() {
        return id;
    }

    public Date getMake() {
        return make;
    }

    public int getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public int getEngine() {
        return engine;
    }

    private static List<Car> cars=new ArrayList<Car>();
    public Car(String model,Date make,int price,int engine){
        this.model=model;
        this.make=make;
        this.price=price;
        this.engine=engine;
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
    public Car(){
        String URL="jdbc:postgresql://localhost:5432/cars";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(URL, "postgres", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select id,model,make,price,id_engine from CARS.PUBLIC.CAR");
            while (rs.next()) {
                Car car=new Car(rs.getInt("id"),rs.getString("model"),rs.getDate("make"),rs.getInt("price"),rs.getInt("id_engine"));
                cars.add(car);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        for(Car car:cars){
            System.out.println(car.toString());
        }
    }

    public static Car getCarById(int id){

        Car result=null;
        Iterator<Car> iter = cars.iterator();
        while(iter.hasNext()){
            Car c=iter.next();
            if(c.getId()==id){
                result=c;
            }
        }

        return result;
    }
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    public static void insertCar(Car car) throws SQLException {
        String URL="jdbc:postgresql://localhost:5432/cars";

        Connection con = DriverManager.getConnection(URL, "postgres", "root");

        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO CAR (model,make,price,id_engine) VALUES (?,?,?,?)");

            //Set to the desired position values of a certain type
            preparedStatement.setString(1,car.getModel());
            java.sql.Date sDate = convertUtilToSql(car.getMake());
            preparedStatement.setDate(2, sDate);
            preparedStatement.setInt(3,car.getPrice());
            preparedStatement.setInt(4,car.getEngine());

            //Execute query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("PrepareStatement error ", e.getMessage(), e);
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    LOGGER.error("Close connection is failed ", e.getMessage(),e);
                }
            }
        }
        //return result;
    }
    public Car(int id,String model,Date make,int price,int engine){
        this.id=id;
        this.model=model;
        this.make=make;
        this.price=price;
        this.engine=engine;

    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", make=" + make +
                ", price=" + price +
                ", engine=" + engine +
                '}';
    }

    public static void main(String[] args){
        Car c = new Car();
        System.out.println("Введите id машины");
        Scanner read=new Scanner(System.in);
        int id=read.nextInt();
        System.out.println(Car.getCarById(id));
        try {
            Scanner readCar=new Scanner(System.in);
            System.out.println("Введите модель машины");
            String model=readCar.nextLine();
            System.out.println("Введите дату изготовления в формате гггг-мм-дд");
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-mm-dd");
            Date make=format.parse(readCar.nextLine());
            System.out.println("Введите цену");
            int price=readCar.nextInt();
            System.out.println("Введите модель двигателя");
            int engine=readCar.nextInt();
            Car car=new Car(model,make,price,engine);
            Car.insertCar(car);
        } catch (ParseException e) {
            e.printStackTrace();
        }catch(SQLException e){

        }
    }
}
