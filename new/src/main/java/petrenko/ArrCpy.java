package petrenko;

import java.util.Arrays;

/**
 * Created by grpetr189853 on 23.01.2017.
 */
public class ArrCpy {
    public static void main(String[] args) {
        int [] arr=new int[10];
        for (int i=0;i<10;i++){
            arr[i]=(int)(Math.random()*100);
        }
        System.arraycopy(arr,0,arr,arr.length-3,3);
        System.out.println(Arrays.toString(arr));
    }
}
