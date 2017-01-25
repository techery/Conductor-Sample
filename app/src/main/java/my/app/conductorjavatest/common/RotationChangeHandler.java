package my.app.conductorjavatest.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

public class RotationChangeHandler implements ControllerChangeHandler.ControllerChangeListener {
    static final String DETAIL_TAG = "detail-tag";

    private StateConnector stateConnector;

    public RotationChangeHandler(StateConnector stateConnector) {
        this.stateConnector = stateConnector;
    }

    @Override
    public void onChangeStarted(@Nullable Controller to,
                                @Nullable Controller from,
                                boolean isPush,
                                @NonNull ViewGroup container,
                                @NonNull ControllerChangeHandler handler) {
    }

    @Override
    public void onChangeCompleted(@Nullable Controller to,
                                  @Nullable Controller from,
                                  boolean isPush,
                                  @NonNull ViewGroup container,
                                  @NonNull ControllerChangeHandler handler) {
        if (!isPush) return;

        Router detailRouter = stateConnector.getDetailRouter();
        Router parentRouter = stateConnector.getParentRouter();

        if (stateConnector.hasDetail()) {
            Controller detailController = parentRouter
                    .getControllerWithTag(DETAIL_TAG);
            if (detailRouter == null) {
                throw new IllegalStateException("Detail router is null");
            }

            if (detailController != null) {
                Bundle state = saveDetailStateBundle(parentRouter, DETAIL_TAG);
                checkState(state);

                parentRouter.popController(detailController);
                detailRouter.setRoot(RouterTransaction.restore(state));
            }
        } else if (detailRouter != null) {
            if (detailRouter.getControllerWithTag(DETAIL_TAG) != null &&
                    parentRouter.getControllerWithTag(DETAIL_TAG) == null) {
                Bundle state = saveDetailStateBundle(detailRouter, DETAIL_TAG);
                checkState(state);

                parentRouter.pushController(RouterTransaction.restore(state));
            }
        }
    }

    @Nullable
    private Bundle saveDetailStateBundle(@NonNull Router router, @NonNull String tag) {
        for (RouterTransaction transaction : router.getBackstack()) {
            if (tag.equals(transaction.tag())) {
                return transaction.saveInstanceState();
            }
        }

        return null;
    }

    private void checkState(Bundle state) {
        if (state == null) {
            throw new IllegalStateException("No state bundle to restore");
        }
    }

    public interface Factory {
        RotationChangeHandler create();
    }
}
