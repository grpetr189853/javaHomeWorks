package petrenko;
import java.util.*;
/**
 * Created by grpetr189853 on 22.01.2017.
 */
public class MaxMin {
    public static void main(String[] args) {
        Scanner reader =new Scanner(System.in);
        int [] a = new int[10];
        for(int i=0;i<10;i++){
            System.out.println("Введите число");
            a[i] = reader.nextInt();
        }
        int max=a[0];
        int min=a[0];
        for(int i=0;i<10;i++){
            if(a[i]<min){
                min=a[i];
            }
        }
        for(int i=0;i<10;i++){
            if(a[i]>max){
                max=a[i];
            }
        }
        System.out.println("Минимальное число " + min);
        System.out.println("Максимальное число "+ max);
/*        for(int i=0;i<10;i++){
            System.out.println(a[i]);
        }*/
    }
}
