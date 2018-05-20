package petrenko;

import java.util.concurrent.CountDownLatch;

/**
 * Created by grpetr189853 on 08.02.2017.
 */
public class Task3 {
    private static final CountDownLatch START = new CountDownLatch(15);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 5; i++) {
            new Thread(new Car(i)).start();

            START.countDown();
            Thread.yield();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(new Customer(i)).start();
            START.countDown();
            Thread.yield();
        }
        new Thread(new Auction()).start();
        Thread.sleep(1000);

    }
    static class Auction implements Runnable {

        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Аукцион начался!");
            } catch (InterruptedException e) {
            }
        }
    }
    static  class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        public void run() {
            try {
                System.out.printf("Автомобиль №%d подъехал аукциону.\n", carNumber);
                START.countDown();
                Thread.sleep(1000);
                START.await();
                System.out.printf("Автомобиль №%d cнят с продажи.\n", carNumber);
            } catch (Exception e) {
            }
        }
    }
    static  class Customer implements Runnable{
        private int customerNumber;
        public Customer(int customerNumber){
            this.customerNumber=customerNumber;
        }
        public void run(){
            try{
                System.out.printf("Покупатель № %d подошел к аукциону\n",customerNumber);
                START.countDown();
                Thread.sleep(1000);
                START.await();
                System.out.printf("Покупатель № %d ушел\n",customerNumber);
            }catch (Exception ie){

            }
        }
    }
}
