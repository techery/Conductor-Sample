package my.app.conductorjavatest.common;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import my.app.conductorjavatest.controller.BaseController;
import my.app.conductorjavatest.util.ControllerUtil;
import nucleus.presenter.Presenter;

/**
 * To use this class you have to annotate child using {@link MasterDetail} annotation
 */
public abstract class MasterDetailBaseController<P extends Presenter<?>> extends BaseController<P>
        implements StateConnector {
    private static final String MASTER_TAG = "master";
    private static final String DETAIL_TAG = "detail";

    @NonNull
    private Router masterRouter;
    @Nullable
    private Router detailRouter;

    @NonNull
    private Router parentRouter;

    private boolean hasDetail;

    private RotationChangeHandler rotationChangeHandler = new RotationChangeHandler(this);

    @CallSuper
    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        MasterDetail masterDetailAnnotation = ControllerUtil.findAnnotationInClass(getClass(), MasterDetail.class);
        if (masterDetailAnnotation == null) {
            throw new IllegalStateException("MasterDetail annotation hasn't been found");
        }

        ViewGroup detailContainer = (ViewGroup) view.findViewById(masterDetailAnnotation.detailContainerId());
        hasDetail = detailContainer != null;

        parentRouter = getParentController().getRouter();
        masterRouter = getChildRouter((ViewGroup) view.findViewById(masterDetailAnnotation.masterContainerId()),
                MASTER_TAG);
        if (hasDetail) {
            detailRouter = getChildRouter(detailContainer, DETAIL_TAG);
        }

        parentRouter.addChangeListener(rotationChangeHandler);
    }

    @CallSuper
    @Override
    protected void onDestroyView(@NonNull View view) {
        parentRouter.removeChangeListener(rotationChangeHandler);
        super.onDestroyView(view);
    }

    @Override
    public final boolean hasDetail() {
        return hasDetail;
    }

    @NonNull
    @Override
    public final Router getParentRouter() {
        return parentRouter;
    }

    @Nullable
    @Override
    public final Router getDetailRouter() {
        return detailRouter;
    }

    public final Router getMasterRouter() {
        return masterRouter;
    }

    protected static void navigateDetail(@NonNull Router router,
                                         @NonNull Controller controller,
                                         @NonNull NavigationType navigationType,
                                         @Nullable ControllerChangeHandler pushHandler,
                                         @Nullable ControllerChangeHandler popHandler) {
        RouterTransaction transaction = RouterTransaction.with(controller)
                .tag(RotationChangeHandler.DETAIL_TAG)
                .pushChangeHandler(pushHandler)
                .popChangeHandler(popHandler);

        switch (navigationType) {
            case PUSH:
                router.pushController(transaction);
                break;
            case REPLACE_TO_TOP:
                router.replaceTopController(transaction);
                break;
        }
    }

    protected static void navigateDetail(@NonNull Router router,
                                         @NonNull Controller controller,
                                         @NonNull NavigationType navigationType) {
        navigateDetail(router, controller, navigationType, null, null);
    }

    protected enum NavigationType {
        PUSH, REPLACE_TO_TOP
    }
}
