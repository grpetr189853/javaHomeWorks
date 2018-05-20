package petrenko;

import java.util.LinkedHashMap;
import java.util.Map;
//Получение сотрудника по уникальному ключу
/**
 * Created by grpetr189853 on 01.02.2017.
 */
public class Employee {
    private String name="";
    public Employee(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public static void main(String[] args) {
        Map staff = new LinkedHashMap();
        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Gary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));
        Employee e = (Employee) staff.get("144-25-5464");
        System.out.println(e.getName());
        Employee e1 = (Employee) staff.get("567-24-2546");
        System.out.println(e1.getName());
        Employee e2 = (Employee) staff.get("157-62-7935");
        System.out.println(e2.getName());
        Employee e3 = (Employee) staff.get("456-62-5527");
        System.out.println(e3.getName());
    }
}
