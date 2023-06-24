import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggingProxy {
    static TestLoggingInterface createTestLoggingInstance() {
        InvocationHandler handler = new LoggingInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(LoggingProxy.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }


    static class LoggingInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLogging;

        public LoggingInvocationHandler(TestLoggingInterface testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method methodFromImpl = testLogging.getClass().
                    getMethod(method.getName(), Arrays.stream(args).
                            map(Object::getClass).
                            toList().
                            toArray(new Class[args.length]));


            boolean isContainsLog = Arrays.stream(methodFromImpl.getAnnotations()).
                    anyMatch(annotation -> annotation.annotationType().getName().equals("Log"));
            StringBuffer consoleOut = new StringBuffer();

            if (isContainsLog) {
                consoleOut.append("Executed method: ").
                        append(method.getName()).
                        append(", param: ");
                if (args.length > 0) {
                    for (int i = 0; i < args.length - 1; i++) {
                        consoleOut.append(args[i]).append(", ");
                    }
                    consoleOut.append(args[args.length - 1]).append("\n");
                } else {
                    consoleOut.append("none");
                }
            }

            System.out.println(consoleOut);

            return method.invoke(testLogging, args);
        }
    }


}
