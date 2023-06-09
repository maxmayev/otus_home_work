import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            Method methodFromImpl = testLogging.getClass().getMethod(method.getName(), Arrays.stream(args).map(Object::getClass).collect(Collectors.toList()).toArray(new Class[args.length]));

            if (Arrays.stream(methodFromImpl.getAnnotations()).anyMatch(annotation -> annotation.annotationType().getName().equals("Log"))) {
                System.out.print("Executed method: " + method.getName() + ", param: ");
                if (args.length > 0) {
                    for (int i = 0; i < args.length - 1; i++) {
                        System.out.print(args[i] + ", ");
                    }
                    System.out.print(args[args.length - 1] + "\n");
                } else {
                    System.out.print("none");
                }
            }
            return method.invoke(testLogging, args);
        }
    }


}
