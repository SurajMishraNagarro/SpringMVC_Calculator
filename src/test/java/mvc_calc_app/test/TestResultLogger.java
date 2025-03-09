package mvc_calc_app.test;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestResultLogger extends TestWatcher {
    // private static final String FILE_PATH = "C:\\Users\\surajmishra\\DevOps Assignments\\week 3-4\\Jenkins\\Test Results\\TestResultRecord.txt";
    private static final String FILE_PATH = "TestResults/TestResultRecord.txt";

    @Override
    protected void starting(Description description) {
        log("\n--- Spring MVC Calculator Test Results ---");
    }

    @Override
    protected void succeeded(Description description) {
        log(getTimestamp() + " - " + description.getMethodName() + " - PASSED");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        log(getTimestamp() + " - " + description.getMethodName() + " - FAILED: " + e.getMessage());
    }

    private void log(String message) {
    File file = new File(FILE_PATH);
    File parentDir = file.getParentFile();
    if (!parentDir.exists()) {
        parentDir.mkdirs();
    }
    try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
        pw.println(message);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
