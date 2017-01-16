package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.conductor.Detail;
import my.app.conductorjavatest.conductor.Master;

public abstract class MasterDetailController extends Controller {
    private Router masterRouter;
    private Router childRounter;

    private boolean openDetailIfPossible;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View rootView = createView(inflater, container);

        Master masterAnnotation = getMasterFromAnnotation(this.getClass());
        View masterView = rootView.findViewById(masterAnnotation.containerId());
        if (!(masterView instanceof ViewGroup)) {
            throw new IllegalArgumentException("Master cannot be a View");
        }


        masterRouter = getChildRouter((ViewGroup) masterView, masterAnnotation.tag());

        return rootView;
    }

    protected abstract View createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    /**
     * Recursively scans class hierarchy searching for {@link Layout} annotation defined.
     *
     * @param clazz class to search for annotation
     * @return defined layout if any or <b>null</b>
     */
    @Nullable
    private Master getMasterFromAnnotation(Class clazz) {
        if (clazz == null || clazz.equals(Object.class)) return null;

        Master layout = (Master) clazz.getAnnotation(Master.class);
        if (layout != null) {
            return layout;
        } else {
            return getMasterFromAnnotation(clazz.getSuperclass());
        }
    }

    /**
     * Recursively scans class hierarchy searching for {@link Layout} annotation defined.
     *
     * @param clazz class to search for annotation
     * @return defined layout if any or <b>null</b>
     */
    @Nullable
    private Detail getDetailFromAnnotation(Class clazz) {
        if (clazz == null || clazz.equals(Object.class)) return null;

        Detail layout = (Detail) clazz.getAnnotation(Detail.class);
        if (layout != null) {
            return layout;
        } else {
            return getDetailFromAnnotation(clazz.getSuperclass());
        }
    }
}