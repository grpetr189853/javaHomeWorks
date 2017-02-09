package petrenko;

import java.util.ArrayList;

/**
 * Created by grpetr189853 on 06.02.2017.
 */
public class Task2 {
    public static void main(String[] args) {
        Store firstStore=new Store(1000);
//        Thread storeThread=new Thread(firstStore);
        Provider prov=new Provider("Provider",firstStore);
        Shop1 firstShop=new Shop1("first shop",100,firstStore);
        Shop2 secondShop=new Shop2("second shop",100,firstStore);
        Thread p1=new Thread(prov);
        Thread sh1=new Thread(firstShop);
        Thread sh2=new Thread(secondShop);

//            firstStore.setApples(1000-firstShop.getQuantity()- secondShop.getQuantity());
//            storeThread.start();
        p1.start();
        sh1.start();
        sh2.start();

        Thread.yield();

    }
}
class Store{
    public volatile int apples;
    public synchronized void addApples(Store store) {
        store.apples++;
    }
    public static Thread storeThread;
    public synchronized void remApples(Store store) {
        store.apples--;
    }
    public Store(int apples) {
        this.apples = apples;
    }
    public int getApples() {
        return apples;
    }
    public void setApples(int apples){
        this.apples=apples;
    }
/*
    public void run(){
        for(int i=0;i<100;i++) {
            try {

                System.out.printf("На складе яблоки в количестве %d\n", this.getApples());


                Thread.sleep(100);
                this.addApples(this);



            } catch (InterruptedException ie) {

            }
        }
    }
*/

}
class Provider implements Runnable{
    private String providerName="";
    private Store store;
    public Provider(String providerName,Store store){
        this.providerName=providerName;
        this.store=store;
    }
    public void run(){
        for(int i=0;i<1000;i+=10){
            try{
                System.out.printf("Поставщик %s поставил на слад %d кг яблок\n",providerName,i);
                store.setApples(i);
                Thread.sleep(100);
            }catch(InterruptedException ie){

            }
        }
    }
}
class Shop1 implements Runnable {
    protected String shopName="";
    protected int quantity=0;
    protected Store store;
    public int getQuantity(){
        return this.quantity;
    }
    public Shop1(String shopName,int quantity,Store store){
        this.shopName=shopName;
        this.quantity=quantity;
        this.store=store;
    }
    public void run() {


        for (int i = 0; i < 100; i++) {
            try {
                System.out.printf("Магазин %s обратился на склад и продал %d кг яблок\n", shopName, i);
                store.remApples(store);
                System.out.printf("На складе осталось %d кг яблок\n",store.getApples());
                Thread.sleep(quantity);
                quantity=i;
            } catch (InterruptedException ie) {

            }
        }
        System.out.printf("\"У магазина %s закончились яблоки\n", shopName);
    }

}
class Shop2 extends Shop1{
    public Shop2(String shopName,int quantity,Store store){
        super(shopName,quantity,store);
    }
    public void run() {

        for (int i = 0; i < 100; i++) {
            try {
                System.out.printf("Магазин %s обратился на склад и продал %d кг яблок\n", shopName, i);
                store.remApples(store);
                System.out.printf("На складе осталось %d кг яблок\n",store.getApples());
                Thread.sleep(2 * quantity);
                quantity=i;
            } catch (InterruptedException ie) {

            }
        }
        System.out.printf("У магазина %s закончились яблоки\n", shopName);
    }

}
