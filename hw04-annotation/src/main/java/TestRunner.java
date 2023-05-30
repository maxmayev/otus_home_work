import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {

    public static HashMap<String, Integer> runTests(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        HashMap<String, Integer> successFailsCounter = new HashMap<>();
        successFailsCounter.put("Success", 0);
        successFailsCounter.put("Fails", 0);

        Class c = Class.forName(name);
        Object object = c.newInstance();

        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        List<Method> beforeMethods = getBeforeMethods(methods);

        List<Method> testMethods = getTestMethods(methods);

        List<Method> afterMethods = getAfterMethods(methods);

        for (Method method : beforeMethods
        ) {
            try {
                method.invoke(object);
            } catch (Exception e) {
                System.out.println("Before method: " + method.getName() + " fails");
            }
        }

        for (Method method : testMethods
        ) {
            try {
                method.invoke(object);
                successFailsCounter.put("Success", successFailsCounter.get("Success") + 1);
            } catch (Exception exception) {
                successFailsCounter.put("Fails", successFailsCounter.get("Fails") + 1);
                System.out.println("Test with name: " + method.getName() + " fails");
            }
        }

        for (Method method : afterMethods
        ) {
            try {
                method.invoke(object);
            } catch (Exception exception) {
                System.out.println("After method: " + method.getName() + " fails");
            }
        }

        return successFailsCounter;
    }

    private static List<Method> getBeforeMethods(Method[] methods) {
        return Arrays.stream(methods).filter(method -> Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType().getName().equals("Before"))).collect(Collectors.toList());

    }

    private static List<Method> getTestMethods(Method[] methods) {
        return Arrays.stream(methods).filter(method -> Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType().getName().equals("Test"))).collect(Collectors.toList());

    }

    private static List<Method> getAfterMethods(Method[] methods) {
        return Arrays.stream(methods).filter(method -> Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType().getName().equals("After"))).collect(Collectors.toList());

    }


}
