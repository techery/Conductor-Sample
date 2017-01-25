package my.app.conductorjavatest.common;

import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MasterDetail {
    @IdRes int masterContainerId();

    @IdRes int detailContainerId();

    boolean isDetailFullScreen() default false;
}
