package my.app.conductorjavatest.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import butterknife.BindView;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MerchantsMasterDetailPresenter;
import my.app.conductorjavatest.view.MerchantsMasterDetailView;
import nucleus.factory.RequiresPresenter;
import timber.log.Timber;

@RequiresPresenter(MerchantsMasterDetailPresenter.class)
@Layout(R.layout.merchants_master_detail)
public class MasterDetailController extends BaseController<MerchantsMasterDetailPresenter>
        implements MerchantsMasterDetailView, MasterDetailNavigator {
    private static final String DETAIL_TAG = "detail";

    @BindView(R.id.masterContainer)
    ViewGroup masterContainer;
    @Nullable
    @BindView(R.id.detailContainer)
    ViewGroup detailContainer;

    private Router detailRouter;
    private Router parentRouter;

    private ControllerChangeHandler.ControllerChangeListener listener;

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        parentRouter = getParentController().getRouter();

        Router merchantMasterRouter = getChildRouter(masterContainer, "merchants_master");

        if (!merchantMasterRouter.hasRootController()) {
            merchantMasterRouter.setRoot(RouterTransaction.with(new MasterListController()));
        }

        final boolean hasDetail = detailContainer != null;
        if (hasDetail) {
            detailRouter = getChildRouter(detailContainer, "merchants_detail");

            if (!detailRouter.hasRootController()) {
                Timber.i("Pushing default controller");
                detailRouter.setRoot(RouterTransaction.with(new MapController()));
            }
        }

        listener = new ControllerChangeHandler.ControllerChangeListener() {
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

                if (hasDetail) {
                    Controller detailController = parentRouter.getControllerWithTag(DETAIL_TAG);

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
        };

        parentRouter.addChangeListener(listener);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        parentRouter.removeChangeListener(listener);
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

    @Override
    public void navigate() {
        if (detailContainer == null) {
            parentRouter
                    .pushController(RouterTransaction.with(new DetailController())
                            .tag(DETAIL_TAG)
                            .pushChangeHandler(new HorizontalChangeHandler())
                            .popChangeHandler(new HorizontalChangeHandler()));

        } else {
            detailRouter.setRoot(RouterTransaction.with(new DetailController())
                    .tag(DETAIL_TAG));
        }
    }

    private void checkState(Bundle state) {
        if (state == null) {
            throw new IllegalStateException("No state bundle to restore");
        }
    }
}
