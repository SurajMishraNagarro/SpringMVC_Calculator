package mvc_calc_app.test;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestResultLogger extends TestWatcher {
    private static final String FILE_PATH = "\"C:\\Users\\surajmishra\\DevOps Assignments\\week 3-4\\Jenkins\\Test Results\""; // File to store results

    @Override
    protected void succeeded(Description description) {
        logResult(description.getMethodName(), "PASSED");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        logResult(description.getMethodName(), "FAILED: " + e.getMessage());
    }

    private void logResult(String testName, String result) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(testName + " - " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
