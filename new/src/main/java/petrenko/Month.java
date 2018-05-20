package petrenko;

import java.util.Scanner;

/**
 * Created by grpetr189853 on 25.01.2017.
 */
public class Month {
    public static void main(String[] args) {
        Scanner read=new Scanner(System.in);
        System.out.println("Введите номер месяца года");
        int monthNum=read.nextInt();
        switch (monthNum){
            case 12:
            case 1:
            case 2:
                System.out.println("Зима");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Весна");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("Лето");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("Осень");
                break;
        }
    }
}
