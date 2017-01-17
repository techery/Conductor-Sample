package my.app.conductorjavatest;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private static Map<Class<?>, Method> cache = new HashMap<>();

    private Object component;

    public Injector(Object component) {
        this.component = component;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void inject(Object injectableObject) {
        Class<?> objectClass = injectableObject.getClass();
        try {
            Method injectableMethod = findInjectableMethod(objectClass);
            assert injectableMethod != null;

            injectableMethod.invoke(component, injectableObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException | NullPointerException e) {
            String detailMessage = "No graph method found to inject "
                    + objectClass.getSimpleName() + ". Check your component";
            throw new RuntimeException(detailMessage, e);
        }
    }

    public boolean isInjectable(Object injectableObject) {
        try {
            return findInjectableMethod(injectableObject.getClass()) != null;
        } catch (Exception exception) {
            return false;
        }
    }

    @Nullable
    private Method findInjectableMethod(Class<?> objectClass) throws NoSuchMethodException {
        Method cachedMethod = cache.get(objectClass);
        Class<?> componentClazz = component.getClass();

        if (cachedMethod != null) {
            return componentClazz.getDeclaredMethod(cachedMethod.getName(), objectClass);
        }

        // Find proper injectable method of component to inject presenter instance
        for (Method method : componentClazz.getDeclaredMethods()) {
            for (Class<?> parameterType : method.getParameterTypes()) {

                if (parameterType == objectClass) {
                    cache.put(objectClass, method);
                    return method;
                }
            }
        }

        return null;
    }
}
