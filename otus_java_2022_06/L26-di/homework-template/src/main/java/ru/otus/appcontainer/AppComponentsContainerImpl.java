package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<String, String> nameMap = new HashMap<>();

    public AppComponentsContainerImpl(Class<?>... initialConfigClass) {
        for (Class<?> configClass : initialConfigClass) {
            try {
                processConfig(configClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void processConfig(Class<?> configClass) throws IllegalAccessException, InvocationTargetException {
        checkConfigClass(configClass);
        Method[] methods = configClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (List.of(methods[i].getDeclaredAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().getSimpleName().equals("AppComponent"))) {
                appComponents.add(methods[i]);
            }
        }
        appComponents.sort(Comparator.comparingInt(method -> ((Method) method).getAnnotation(AppComponent.class).order()));
        for (int i = 0; i < appComponents.size(); i++) {
            Method method = (Method) appComponents.get(i);
            Parameter[] parameters = method.getParameters();
            String annotationName = ((Method) method).getAnnotation(AppComponent.class).name();
            ArrayList<Object> methodParams = new ArrayList<>();

            for (int j = 0; j < parameters.length; j++) {
                methodParams.add(getAppComponent(parameters[j].getType()));
            }

            try {
                Object classInstance = configClass.newInstance();
                Object invokeResult = method.invoke(classInstance, methodParams.toArray());
                appComponentsByName.put(method.getReturnType().getName(), invokeResult);
                nameMap.put(annotationName, method.getReturnType().getName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        if (componentClass == null) return null;

        C component = (C) appComponentsByName.get(componentClass.getName());
        if (component == null) {
            Class<?>[] interfaces = componentClass.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                Object cpnt = appComponentsByName.get(interfaces[i].getName());
                if (cpnt != null) component = (C) cpnt;
            }
        }
        return component;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        C coponent = (C) appComponentsByName.get(componentName);
        if (coponent == null && nameMap.get(componentName) != null) {
            coponent = (C)appComponentsByName.get(nameMap.get(componentName));
        }
        return coponent;
    }
}
