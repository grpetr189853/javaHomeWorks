package petrenko;


import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by grpetr189853 on 22.01.2017.
 */
public class Bubble {
    public static void bubbleSort(int [] a){
        for (int i = a.length-1;i > 0;i--){
            for(int j=0;j<i;j++){
                if(a[j]<a[j+1]){
                    int tmp;
                    tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int [] arr = new int[10];
        for (int i=0;i<10;i++) {
            System.out.println("Введите число");
            Scanner read = new Scanner(System.in);
            arr[i] = read.nextInt();
        }
        System.out.println("Массив до сортироки");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("Массив после сортировки");
        System.out.println(Arrays.toString(arr));
    }
}
