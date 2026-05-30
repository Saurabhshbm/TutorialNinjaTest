package Listeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Test Execution Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test Passed : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test Failed : " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test Execution Completed");
    }
}

