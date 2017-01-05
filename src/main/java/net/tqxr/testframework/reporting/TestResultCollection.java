package net.tqxr.testframework.reporting;

import org.junit.Ignore;
import org.junit.runner.Description;

import java.util.HashMap;
import java.util.Map;

import static net.tqxr.utilities.AnsiColorizer.*;

class TestResultCollection {
    private HashMap<String, HashMap<String, TestResult>> testResults;
    private TestNameTransformer testNameTransformer;

    TestResultCollection() {

        testResults = new HashMap<>();
        testNameTransformer = new TestNameTransformer();

        TestResultCollection me = this;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println(me);
            }
        });
    }

    void addTestResult(Description description) {
        String classKey = description.getClassName();
        String key = description.getMethodName();

        if (!testResults.containsKey(classKey)) {
            testResults.put(classKey, new HashMap<>());
        }

        Map<String, TestResult> resultMap = testResults.get(classKey);
        TestResult t = new TestResult(description);
        resultMap.put(key, t);
    }

    TestResult getTestResult(Description description) {
        String className = description.getClassName();
        String testName = description.getMethodName();
        return testResults.get(className).get(testName);
    }


    @Override
    public String toString() {

        int failuresCount = 0;

        StringBuilder outputString = new StringBuilder();
        outputString.append("\n");

        for (String eachKey : testResults.keySet()) {
            Map<String, TestResult> resultMap = testResults.get(eachKey);
            outputString.append(String.format(
                    "\n%s%s%s\n",
                    CYAN.underline(),
                    eachKey,
                    RESET
            ));

            for (String eachTestKey : resultMap.keySet()) {
                String testWords = testNameTransformer.transformTestName(eachTestKey);
                TestResult t = resultMap.get(eachTestKey);
                if (t.isIgnored()) {
                    testWords = String.format("%s%s (%s) %s",
                            BLACK.bright(),
                            testWords,
                            t.getDescription().getAnnotation(Ignore.class).value(),
                            RESET);
                }
                if (t.isFailed()) {
                    failuresCount++;
                    testWords = String.format("%s%s (%s) %s",
                            RED.bright(),
                            testWords,
                            t.getFailure().getMessage(),
                            RESET)
                    ;
                }
                String testStatus = String.format("%s",
                        testNameTransformer.passFailSkip(t.isFailed(), t.isIgnored(), t.isErrored()));
                outputString.append(String.format("%s %s\n", testStatus, testWords));
            }
        }

        if (failuresCount != 0) {
            outputString.append(String.format("\n\n%sFAILURES: %d%s\n\n",
                    RED.underline(),
                    failuresCount,
                    RESET));
        } else {
            outputString.append(String.format("\n\n%s%s%s\n\n",
                    GREEN.underline(),
                    "SUCCESS",
                    RESET));
        }

        return outputString.toString();
    }
}
