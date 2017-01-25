package my.app.conductorjavatest.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bluelinelabs.conductor.Router;

public interface StateConnector {
    boolean hasDetail();

    @NonNull
    Router getParentRouter();

    @Nullable
    Router getDetailRouter();
}
