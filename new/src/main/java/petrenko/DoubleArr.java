package petrenko;

/**
 * Created by grpetr189853 on 22.01.2017.
 */
public class DoubleArr {
    public static void main(String[] args) {
        int [][] arr = new int[5][8];

        for(int i=0;i<5;i++){
            for(int j=0;j<8;j++){
                arr[i][j]= (int)(Math.random()*100);
            }
        }
        int Sum=0;
        for (int i=0;i<5;i++){
            for (int j=0;j<8;j++){
                Sum+=arr[i][j];
            }
        }
        System.out.println("Сумма чисел равна "+ Sum);
    }
}
