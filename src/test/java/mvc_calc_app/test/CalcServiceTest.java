package mvc_calc_app.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import mvc_calc_app.CalcService;
import static org.junit.Assert.assertThrows;

public class CalcServiceTest {

    private CalcService calcService; // obj for this class type

    @Before  // this will run each time before any test
    public void setUp() {
        calcService = new CalcService();  // initaite the object of clacService
    }

    @Test
    public void addTest() {
        double result = calcService.performOperation(5, 3, "add");
        assertEquals(8.0, result, 0.001); // delta is 0.001 for floating-point precision
    }

    @Test
    public void subTest() {
        double result = calcService.performOperation(10, 4, "sub");
        assertEquals(6.0, result, 0.001);
    }
    
    @Test
    public void mulTest() {
        double result = calcService.performOperation(6, 2, "mul");
        assertEquals(12.0, result, 0.001);
    }

    @Test
    public void divTest() {
        double result = calcService.performOperation(9, 3, "div");
        assertEquals(3.0, result, 0.001);
    }

}
