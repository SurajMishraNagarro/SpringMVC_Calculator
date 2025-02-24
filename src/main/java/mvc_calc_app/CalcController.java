package mvc_calc_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {

    @Autowired
    private CalcService calcService; // to use over service class

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {
        try {
            
        	//calling the func here
        	double result = calcService.performOperation(num1, num2, operation);
            // adding the result in the model so to send this to view
        	model.addAttribute("num1", num1);
            model.addAttribute("num2", num2);
            model.addAttribute("operation", operation);
            model.addAttribute("result", result);
        } catch (ArithmeticException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "result";
    }
}
