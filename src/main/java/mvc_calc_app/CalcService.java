package mvc_calc_app;

import org.springframework.stereotype.Service;

@Service
public class CalcService {

    
	// in this one function, it will take 3 var , numbs and operations
	// this func has the ability to thorw an Arithmetic Expression based on the specific condition
	public double performOperation(double num1, double num2, String operation) throws ArithmeticException {
        switch (operation) {
            case "add":
                return num1 + num2;
            case "sub":
                return num1 - num2;
            case "mul":
                return num1 * num2;
            case "div":
                if (num2 == 0) {
                    // throw - to raise an exception
                	throw new ArithmeticException("Cannot divide by zero");
                }
                return num1 / num2;
            
            // when the operation does not match with our switch cases
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
}
