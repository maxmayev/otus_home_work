public class Demo {

    TestLoggingInterface testLogging = LoggingProxy.createTestLoggingInstance();

    public void action() {
        testLogging.calculation(6);
    }

    public void action2() {
        testLogging.calculation(6, 7);
    }

    public void action3() {
        testLogging.calculation(6, 7, 8);
    }

}
