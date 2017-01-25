package my.app.conductorjavatest.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.util.ControllerUtil;
import nucleus.factory.PresenterFactory;
import nucleus.factory.ReflectionPresenterFactory;
import nucleus.presenter.Presenter;
import nucleus.view.PresenterLifecycleDelegate;
import nucleus.view.ViewWithPresenter;

public abstract class BaseController<P extends Presenter> extends Controller
        implements ViewWithPresenter<P> {

    private Unbinder unbinder;

    private PresenterLifecycleDelegate<P> presenterLifecycleDelegate;
    private final LifecycleListener lifecycleListener = new LifecycleListener() {
        @Override
        public void preCreateView(@NonNull Controller controller) {
            presenterLifecycleDelegate = new PresenterLifecycleDelegate<>(presenterFactory());
        }
    };

    public BaseController() {
        addLifecycleListener(lifecycleListener);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Layout layout = ControllerUtil.findAnnotationInClass(getClass(), Layout.class);
        if (layout == null) {
            throw new IllegalArgumentException("Controller should have Layout annotation");
        }

        View view = inflater.inflate(layout.value(), container, false);
        unbinder = ButterKnife.bind(this, view);


        presenterLifecycleDelegate.onResume(this);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(@NonNull View view) {
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        presenterLifecycleDelegate.onDropView();
        super.onDestroyView(view);

        unbinder.unbind();
        unbinder = null;
    }

    @Override
    protected void onDestroy() {
        removeLifecycleListener(lifecycleListener);
        presenterLifecycleDelegate.onDestroy(isBeingDestroyed());
        super.onDestroy();
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        Icepick.restoreInstanceState(this, savedViewState);
    }

    @Override
    public PresenterFactory<P> getPresenterFactory() {
        return presenterLifecycleDelegate.getPresenterFactory();
    }

    @Override
    public void setPresenterFactory(PresenterFactory<P> presenterFactory) {
        presenterLifecycleDelegate.setPresenterFactory(presenterFactory);
    }

    @Nullable
    @Override
    public P getPresenter() {
        return presenterLifecycleDelegate.getPresenter();
    }

    protected PresenterFactory<P> presenterFactory() {
        return ReflectionPresenterFactory.fromViewClass(getClass());
    }
}
