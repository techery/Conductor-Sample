package my.app.conductorjavatest.util;

import android.support.annotation.Nullable;

public final class ControllerUtil {
    private ControllerUtil() {
    }

    /**
     * Recursively scans class hierarchy searching for annotation defined.
     *
     * @param clazz class to search for annotation
     * @return defined layout if any or <b>null</b>
     */
    @Nullable
    public static <T> T findAnnotationInClass(Class clazz, Class<T> targetAnnotation) {
        if (clazz == null || clazz.equals(Object.class)) return null;

        T layout = (T) clazz.getAnnotation(targetAnnotation);
        if (layout != null) {
            return layout;
        } else {
            return findAnnotationInClass(clazz.getSuperclass(), targetAnnotation);
        }
    }
}
