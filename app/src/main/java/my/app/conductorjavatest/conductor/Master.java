package my.app.conductorjavatest.conductor;

import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface Master {
    @IdRes int containerId();

    String tag();
}
