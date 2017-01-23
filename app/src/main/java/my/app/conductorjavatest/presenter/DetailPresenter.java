package my.app.conductorjavatest.presenter;

import javax.inject.Inject;

import my.app.conductorjavatest.service.Interactor;
import my.app.conductorjavatest.view.MerchantDetailView;
import nucleus.presenter.Presenter;

public class DetailPresenter extends Presenter<MerchantDetailView> {
    @Inject
    Interactor interactor;

    @Override
    protected void onTakeView(MerchantDetailView merchantsListView) {
        super.onTakeView(merchantsListView);

        interactor.perform();
    }
}
