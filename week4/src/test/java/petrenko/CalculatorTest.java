package petrenko;

import java.util.InputMismatchException;

import static org.junit.Assert.*;

/**
 * Created by grpetr189853 on 13.02.2017.
 */
public class CalculatorTest {

    @org.junit.Test
    public void testDoMultiply() throws Exception {
        Calculator calc=new Calculator();
        assertEquals(50.00, calc.doCalculation(10.00, 5.00,"*"),0.001);
        assertEquals(70.00, calc.doCalculation(10.00, 7.00,"*"),0.001);
    }
    @org.junit.Test
    public void testDoDivide() throws Exception{
        Calculator calc=new Calculator();
        assertEquals(33.3333, calc.doCalculation(100.00, 3.00,"/"),0.001);
        assertEquals(20.00, calc.doCalculation(100, 5.00,"/"),0.001);
    }
    @org.junit.Test
    public void testDoAddition() throws Exception {
        Calculator calc=new Calculator();
        assertEquals(50.00, calc.doCalculation(25.00,25.00,"+"),0.001);
        assertEquals(150.00,calc.doCalculation(100,50,"+"),0.001);
    }
    @org.junit.Test
    public void testDoSubstraction() throws Exception{
        Calculator calc=new Calculator();
        assertEquals(50.00, calc.doCalculation(200.00,150.00,"-"),0.001);
        assertEquals(500.00,calc.doCalculation(700,200,"-"),0.001);
    }
/*    @org.junit.Test(expected = ArithmeticException.class)
    public void testExceptions() throws Exception{
        Calculator calc=new Calculator();
        calc.doCalculation(100.00,0.00,"/");
    }*/
/*    @org.junit.Test(expected = java.util.InputMismatchException.class)
    public void testException() throws Exception{
        Calculator calc=new Calculator();
        calc.doCalculation(100.00,50.00,"5656");
    }*/
    @org.junit.Test
    public void testDivisionByZero() throws Exception{
        Calculator calculator=new Calculator();
        assertEquals(Double.POSITIVE_INFINITY,calculator.doCalculation(100,0,"/"),0.001);
        assertEquals(Double.NEGATIVE_INFINITY,calculator.doCalculation(-100,0,"/"),0.001);
    }
}