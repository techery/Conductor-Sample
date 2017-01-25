package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.view.View;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.common.MasterDetail;
import my.app.conductorjavatest.common.MasterDetailBaseController;
import my.app.conductorjavatest.presenter.MerchantsMasterDetailPresenter;
import my.app.conductorjavatest.view.MerchantsMasterDetailView;
import nucleus.factory.RequiresPresenter;
import timber.log.Timber;

@RequiresPresenter(MerchantsMasterDetailPresenter.class)
@MasterDetail(masterContainerId = R.id.masterContainer, detailContainerId = R.id.detailContainer)
@Layout(R.layout.merchants_master_detail)
public class MasterDetailImplController extends MasterDetailBaseController<MerchantsMasterDetailPresenter>
        implements MerchantsMasterDetailView, MasterDetailNavigator {

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        Router merchantMasterRouter = getMasterRouter();
        if (!merchantMasterRouter.hasRootController()) {
            merchantMasterRouter.setRoot(RouterTransaction.with(new MasterListController()));
        }

        Router detailRouter = getDetailRouter();
        if (hasDetail() && !detailRouter.hasRootController()) {
            Timber.i("Pushing default controller");
            detailRouter.setRoot(RouterTransaction.with(new MapController()));
        }
    }

    @Override
    public void navigate() {
        if (hasDetail()) {
            navigateDetail(getDetailRouter(), new DetailController(), NavigationType.PUSH);
        } else {
            navigateDetail(getParentRouter(), new DetailController(), NavigationType.PUSH,
                    new HorizontalChangeHandler(), new HorizontalChangeHandler());
        }
    }
}
