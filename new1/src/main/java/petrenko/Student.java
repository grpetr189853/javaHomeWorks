package petrenko;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//Класс который печатает студентов на данном курсе
/**
 * Created by grpetr189853 on 30.01.2017.
 */
public class Student {
    private String name="";
    private int course=0;
    public Student(String name,int course){
        this.name=name;
        this.course=course;
    }
    public String getName(){
        return name;
    }
    public int getCourse(){
        return course;
    }
    public static void printStudents(List<Student> studentList,int course){
        Iterator<Student> it=studentList.iterator();
        while(it.hasNext()){
            Student stud=it.next();
            if(stud.getCourse()==course) {
                System.out.println(stud.getName());
            }
        }

    }
    public static void main(String[] args) {
        ArrayList<Student> studList=new ArrayList<>();
        studList.add(new Student("Богданова Христина Кирилловна",5));
        studList.add(new Student("Степанова Милена Егоровна",3));
        studList.add(new Student("Секунов Кирилл Сергеевич",5));
        studList.add(new Student("Шершов Бронислав Геннадьевич",4));
        studList.add(new Student("Афанасьев Петр Макарович",5));
        studList.add(new Student("Крылов Лев Давидович",4));
        studList.add(new Student("Куколевский Артемий Михайлович",2));
        studList.add(new Student("Воронина Серафима Ильинична",4));
        studList.add(new Student("Федотов Онуфрий Вячеславович",1));
        studList.add(new Student("Русина Маргарита Юрьевна",3));
        printStudents(studList,5);
    }
}
