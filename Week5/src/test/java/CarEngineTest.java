import petrenko.Car;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by grpetr189853 on 22.02.2017.
 */
public class CarEngineTest {
    @org.junit.Test
    public void  TestInsertsCar() throws Exception{
        int id=21;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-mm-dd");
        Date makeDate=format.parse("2010-01-01");
        Car testCar=new Car(id,"Audi",makeDate,200000,3);
        Car.insertCar(testCar);
        Car c = new Car();
        Car c1 = Car.getCarById(id);
        System.out.println(c1);
        assertEquals(c1,testCar);
    }
}
