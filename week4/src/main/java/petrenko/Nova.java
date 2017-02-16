package petrenko;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by grpetr189853 on 11.02.2017.
 */
//Транспортная задача про Новую Почту
public class Nova {
    public static void main(String[] args) throws InterruptedException{
        Posilka ps1=new Posilka(1,true);//Одна посылка в пункте А
        Posilka ps2=new Posilka(10,false);//Десять посылок в пункте В
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
//        for (int i = 0; i < 5; i++) {//Пять автомобилей
        int i=0;
        while (ps1.getQuantity() > 0 && i < 5) {//Пока автомобиль находиться в пунте А и количество посылок там больше 0 автомобиль едет в пункт В
            Auto au1 = new Auto(i, true);

            if (au1.isAB()) {//Если автомобиль в пункте А Уменьшить кол-во посылок в пункте А Увеличить в пункте В и поменять значение автомбиль в пункте A или В на
                //противоположное
                if(ps2.getQuantity()==0) {
                    scheduledExecutorService.scheduleAtFixedRate(new Thread(au1), 1, 1, TimeUnit.SECONDS);//Если количество посылок в пункте В нулевое - нет разг
                }else{
                    scheduledExecutorService.scheduleAtFixedRate(new Thread(au1), 3, 3, TimeUnit.SECONDS);//Если количество не нулевое - дополнительно 2 сек разг
                }
                ps1.remQuantity();
                ps2.addQuantity();
                au1.reverseAB();
            } else {//Иначе увеличить количество посылок в пункте А и поменять значение принадлежности автомобиля А или В
                if(ps2.getQuantity()==0) {
                    scheduledExecutorService.scheduleAtFixedRate(new Thread(au1), 3, 3, TimeUnit.SECONDS);//Если количество посылок в пункте В нулевое - нет разгрузки
                }else{
                    scheduledExecutorService.scheduleAtFixedRate(new Thread(au1), 5, 5, TimeUnit.SECONDS);//Если количество не нулевое - дополнительно 2 сек разгрузка
                }
                au1.reverseAB();
                ps1.addQuantity();
                ps2.remQuantity();
            }
            i++;
            Thread.sleep(1000);
        }
//        }
        ScheduledExecutorService scheduledExecutorService2 =
                Executors.newScheduledThreadPool(10);
//        for (int i = 0; i < 10; i++) {
        int j=5;
        while (ps2.getQuantity() > 0 && j<15) {
            Auto au2 = new Auto(j, false);

            if (au2.isAB()) {
                if(ps1.getQuantity()==0) {
                    scheduledExecutorService2.scheduleAtFixedRate(new Thread(au2), 1, 1, TimeUnit.SECONDS);
                }else{
                    scheduledExecutorService2.scheduleAtFixedRate(new Thread(au2), 3, 3, TimeUnit.SECONDS);
                }
                au2.reverseAB();
                ps2.addQuantity();
                ps1.remQuantity();
            } else {
                if(ps2.getQuantity()==0) {
                    scheduledExecutorService2.scheduleAtFixedRate(new Thread(au2), 3, 3, TimeUnit.SECONDS);
                }else{
                    scheduledExecutorService2.scheduleAtFixedRate(new Thread(au2), 5, 5, TimeUnit.SECONDS);
                }
                au2.reverseAB();
                ps2.remQuantity();
                ps1.addQuantity();
            }
            j++;
            Thread.sleep(1000);
        }
    }
    //    }
    static class Posilka{
        //Посылка - Сколько посылок - quantity - и где находиться посылка - isAorB - true - в пункте А false - в пунте В
        private boolean isAorB;

        public int getQuantity() {
            return quantity;
        }
        public void addQuantity(){
            quantity++;//Увеличивает количество посылок в пункте А или В
        }
        public void remQuantity(){
            quantity--;//Уменьшает количество посылок в пункте А или  В
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        private int quantity;
        public Posilka(int quantity,boolean isAorB){
            this.quantity=quantity;
            this.isAorB=isAorB;
        }
    }
    static class Auto implements Runnable{
        //Автомобиль - номер автомобиля и пункт в котором он находиться - isAB - True - пункт А - False - пункт В
        private int autoNum;
        private boolean isAB;
        public Auto(int autoNum,boolean isAB){
            this.autoNum=autoNum;
            this.isAB=isAB;
        }

        public boolean isAB() {
            return isAB;
        }

        public void setAB(boolean AB) {
            isAB = AB;
        }
        //Метод который меняет пунк в котором находиться автомобиль
        public void reverseAB(){
            this.isAB=!this.isAB;
        }
        public void run() {
            try {
                if(!this.isAB) {
                    System.out.printf("Автомобиль %s доставил посилку из пункта A\n", autoNum);
                }else{
                    System.out.printf("Автомобиль %s доставил посилку из пункта B\n", autoNum);
                }
            }catch(Exception ie){

            }
        }
    }
}