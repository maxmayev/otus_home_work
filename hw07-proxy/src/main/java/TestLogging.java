public class TestLogging implements TestLoggingInterface {

    @Log
    @Override
    public void calculation(Integer param1) {
        System.out.println("Calculated variable: " + param1);
    }

    @Override
    @Log
    public void calculation(Integer param1, Integer param2) {
        System.out.println("Calculated variable: " + (param1 + param2));
    }

    @Override
    public void calculation(Integer param1, Integer param2, Integer param3) {
        System.out.println("\nNot annotated");
        System.out.println("Calculated variable: " + (param1 + param2 + param3));
    }

    @Log
    public void calculation2(Integer param1) {
        System.out.println("Calculated variable: " + param1);
    }


}
