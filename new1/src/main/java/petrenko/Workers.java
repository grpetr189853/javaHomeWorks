package petrenko;

import java.util.ArrayList;
import java.util.Random;
//Работник который случайным образом получает премию
/**
 * Created by grpetr189853 on 29.01.2017.
 */
public class Workers {
    ArrayList<String> work = new ArrayList<>();

    public static void main(String[] args) {
        Workers worker=new Workers();
        worker.work.add("Богданова Христина Кирилловна");
        worker.work.add("Степанова Милена Егоровна ");
        worker.work.add("Секунов Кирилл Сергеевич");
        worker.work.add("Шершов Бронислав Геннадьевич");
        worker.work.add("Афанасьев Петр Макарович");
        worker.work.add("Крылов Лев Давидович");
        worker.work.add("Куколевский Артемий Михайлович");
        worker.work.add("Воронина Серафима Ильинична ");
        worker.work.add("Федотов Онуфрий Вячеславович");
        worker.work.add("Русина Маргарита Юрьевна ");
        Random r= new Random();
        int count = 0;
        String currentElement = "";
        currentElement = worker.work.get(r.nextInt(10));
        System.out.println("Работник которые получит премию :"+currentElement);
    }
}
