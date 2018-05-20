package petrenko;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by grpetr189853 on 08.02.2017.
 */
public class Task5 {
    private static final CountDownLatch START = new CountDownLatch(11);

    public static void main(String[] args) throws InterruptedException{
        for(int i=1;i<=10;i++){
            new Thread(new Friend(i)).start();
            START.countDown();
            Thread.yield();
        }
        Weather wh=new Weather(true);
        new Thread(wh).start();
        if(!wh.isRainy) {
            START.countDown();
            Thread.sleep(1000);

        }else{
            wh.isRainy=false;
            Thread.yield();

        }

        new Thread(new Match()).start();
        Thread.sleep(1000);
    }
    static class Match implements Runnable {
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Матч начался!");
            } catch (InterruptedException e) {
            }
        }
    }

    static class Friend implements Runnable {
        private int friendNumber;

        public Friend(int friendNumber) {
            this.friendNumber = friendNumber;
        }

        public void run() {
            try {
                System.out.printf("Друг № %d пришел\n", friendNumber);
                START.countDown();
                Thread.sleep(1000);
                START.await();
                System.out.printf("Друг № %d ушел\n", friendNumber);
            } catch (Exception e) {

            }
        }

    }

    static class Weather implements Runnable {
        private static boolean isRainy = false;

        public Weather(boolean isRainy) {
            this.isRainy = isRainy;
        }

        public void run() {
            try {
                if (isRainy) {
                    System.out.printf("Идет дождь\n");
                    START.countDown();
                    Thread.sleep(1000);
                    START.await();
                    isRainy = true;
                }else{
                    System.out.printf("Дождь кончился\n");


                }
            }catch(Exception e){

            }
        }
    }
}
