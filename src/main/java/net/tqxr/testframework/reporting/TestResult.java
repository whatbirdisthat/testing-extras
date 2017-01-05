package net.tqxr.testframework.reporting;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

class TestResult {

    private Description description;
    private Failure failure;

    private boolean started;
    private boolean finished;
    private boolean failed;
    private boolean ignored;
    private boolean errored;

    TestResult(Description description) {
        this.started = true;
        this.description = description;
    }

    Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    boolean isFailed() {
        return failed;
    }

    void setFailed(boolean failed) {
        this.failed = failed;
    }

    boolean isIgnored() {
        return ignored;
    }

    void setIgnored(boolean ignored) {
        this.ignored = ignored;
    }


    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isFinished() {
        return finished;
    }

    void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Failure getFailure() {
        return failure;
    }

    void setFailure(Failure failure) {
        this.failure = failure;
    }

    @Override
    public String toString() {

        return "TestResult{" +
                "description=" + description +
                ", failure=" + failure +
                ", started=" + started +
                ", finished=" + finished +
                ", failed=" + failed +
                ", ignored=" + ignored +
                ", errored=" + errored +
                '}';
    }

    public boolean isErrored() {
        return errored;
    }

    public void setErrored(boolean errored) {
        this.errored = errored;
    }
}
