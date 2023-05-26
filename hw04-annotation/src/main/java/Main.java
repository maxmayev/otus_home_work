import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, Integer> successCount = null;
        try {
            successCount = TestRunner.runTests("Tests");
        } catch (ClassNotFoundException e) {
            System.out.println("Class with tests not found");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println("Success tests : " + successCount.get("Success"));
        System.out.println("Fails tests : " + successCount.get("Fails"));
    }
}
