package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.LandingPresenter;
import my.app.conductorjavatest.view.LandingView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LandingPresenter.class)
@Layout(R.layout.landing)
public class LandingController extends BaseController implements LandingView {

    @BindView(R.id.toolbarContainer)
    ViewGroup toolbarContainer;
    @BindView(R.id.contentContainer)
    ViewGroup contentContainer;

    private Router toolbarChildRouter;
    private Router contentChildRouter;

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        toolbarChildRouter = getChildRouter(toolbarContainer, "toolbar");
        contentChildRouter = getChildRouter(contentContainer, "content");

        if (!toolbarChildRouter.hasRootController()) {
            toolbarChildRouter.setRoot(RouterTransaction.with(new ToolbarController()));
        }

        if (!contentChildRouter.hasRootController()) {
            MerchantsMasterDetailController controller = new MerchantsMasterDetailController();
            controller.setRetainViewMode(RetainViewMode.RETAIN_DETACH);

            contentChildRouter.setRoot(RouterTransaction.with(controller));
        }
    }

    @Override
    public void navigateToLocations() {
        getChildRouter(contentContainer, "content").pushController(RouterTransaction.with(new LocationsController()));
    }

    @Override
    public void navigateToMerchants() {
        getChildRouter(contentContainer, "content").setRoot(RouterTransaction.with(new MerchantsMasterDetailController()));
    }

    @Override
    public void navigateToMap() {
        getChildRouter(contentContainer, "content").setRoot(RouterTransaction.with(new MapController()));
    }
}
