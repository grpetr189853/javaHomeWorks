package petrenko;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by grpetr189853 on 06.02.2017.
 */
class FirstThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println("First Thread");
            try{
                Thread.sleep(15);
            }catch(InterruptedException ie){
                System.err.println(ie);
            }
        }
    }
}
class SecondThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<50;i++){
            System.out.println("Second Thread");
            try{
                Thread.sleep(25);
            }catch(InterruptedException ie){
                System.err.println(ie);
            }
        }
    }
}
class ThirdThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<56;i++){
            System.out.println("Third Thread");
            try{
                Thread.sleep(20);
            }catch(InterruptedException ie){
                System.err.println(ie);
            }
        }
    }
}
class ForthThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<32;i++){
            System.out.println("Forth Thread");
            try{
                Thread.sleep(20);
            }catch(InterruptedException ie){
                System.err.println(ie);
            }
        }
    }
}
public class Task1 {
    public static void main(String[] args) {
        FirstThread first=new FirstThread();
        SecondThread second=new SecondThread();
        ThirdThread third=new ThirdThread();
        ForthThread forth=new ForthThread();
        first.start();
        second.start();
        third.start();

        if(third.isAlive()&&second.isAlive()) {
            try {
                second.join();
                third.join();

            } catch (InterruptedException ie) {
                System.out.println("Главный поток прерван");
            }
            forth.start();
        }

    }
}
