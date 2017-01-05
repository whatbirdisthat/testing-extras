package net.tqxr.testframework.reporting;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

@RunListener.ThreadSafe
public class TestResultListener extends RunListener {

    private TestResultCollection testResultCollection;

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        testResultCollection = new TestResultCollection();
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
//        System.out.println(testResultCollection);
    }


    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        testResultCollection.addTestResult(description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
        TestResult t = testResultCollection.getTestResult(description);
        t.setFinished(true);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        TestResult t = testResultCollection.getTestResult(failure.getDescription());
        t.setFailed(true);
        t.setFailure(failure);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);
        System.out.printf("ASSUMPTION FAILURE '%s'\n", failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
        testResultCollection.addTestResult(description);
        TestResult t = testResultCollection.getTestResult(description);
        t.setIgnored(true);
    }
}
