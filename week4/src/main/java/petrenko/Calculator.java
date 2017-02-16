package petrenko;

import java.util.Scanner;

/**
 * Created by grpetr189853 on 12.02.2017.
 */
public class Calculator {
    private Double firstOp=0.00;
    private Double secondOp=0.00;
    private String operation="";
    public Calculator(){

    }
    public Calculator(Double firstOp,Double secondOp,String operation){
        this.firstOp=firstOp;
        this.secondOp=secondOp;
        this.operation=operation;
    }

    public void setFirstOp(Double firstOp) {
        this.firstOp = firstOp;
    }

    public void setSecondOp(Double secondOp) {
        this.secondOp = secondOp;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double doCalculation(double firstOp,double secondOp,String operation){

        double result = 0;
        try {

//            setFirstOp(scanner.nextDouble());
//            setOperation(scanner.nextLine());
//            setSecondOp(scanner.nextDouble());

            switch (operation.charAt(0)) {
                case '+':
                    result = (firstOp + secondOp);
                    break;
                case '-':
                    result = (firstOp - secondOp);
                    break;
                case '*':
                    result = (firstOp * secondOp);
                    break;
                case '/':
                    result = (firstOp / secondOp);
                    break;
            }

        }catch(Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Введите первое выражение");
        Calculator calc=new Calculator();
        Scanner scanner = new Scanner(System.in);
        Double firstOp=scanner.nextDouble();
        Double secondOp=scanner.nextDouble();
        String operation=scanner.next();
        System.out.println(calc.doCalculation(firstOp,secondOp,operation));
    }
}
