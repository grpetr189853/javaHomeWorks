package petrenko;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by grpetr189853 on 08.02.2017.
 */
public class Task4 {
    private static final boolean[] CUSTOMER_PLACES = new boolean[6];
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);
    private static final CountDownLatch START = new CountDownLatch(4);
    public static void main(String[] args) throws InterruptedException{
        for (int i = 1; i <= 7; i++) {
            new Thread(new Customer(i)).start();
            new Thread(new Shop(0)).start();
            Thread.sleep(400);

        }

    }
    static class Shop implements Runnable{
        int customersCount=0;
        public Shop(int customersCount) {
            this.customersCount=customersCount;
        }

        public void run() {
            try {
                Thread.sleep(500);
                for (int i = 0; i < 5; i++) {
                    if (CUSTOMER_PLACES[i]) {
                        customersCount++;
                    }
                }
                if (customersCount > 4) {
                    System.out.printf("Магазин открылся\n");
                    Thread.sleep(1000);
                }
            }catch(Exception e){

            }
        }
    }
    static class Customer implements Runnable{
        private int customerNumber;
        Customer(int customerNumber){
            this.customerNumber=customerNumber;
        }

        public void run() {
            System.out.printf("Покупатель №%d подошел к магазиу.\n", customerNumber);
            try {

                SEMAPHORE.acquire();

                int parkingNumber = -1;

                synchronized (CUSTOMER_PLACES){
                    for (int i = 0; i < 5; i++)
                        if (!CUSTOMER_PLACES[i]) {      //Если место свободно
                            CUSTOMER_PLACES[i] = true;  //занимаем его
                            parkingNumber = i;         //Наличие свободного места, гарантирует семафор
                            System.out.printf("Покупатель №%d занял место  %d в очереди.\n", customerNumber, i);
                            break;
                        }
                }

                Thread.sleep(5000);

                synchronized (CUSTOMER_PLACES) {
                    CUSTOMER_PLACES[parkingNumber] = false;
                }

                SEMAPHORE.release();
                System.out.printf("Покупатель №%d покинул магазин.\n", customerNumber);
                START.countDown();
                START.await();
                System.out.printf("Магазин закрылся\n");
            } catch (InterruptedException e) {
            }
        }
    }
}
