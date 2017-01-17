package my.app.conductorjavatest.presenter;

import javax.inject.Inject;

import my.app.conductorjavatest.service.DtlInteractor;
import my.app.conductorjavatest.view.MerchantDetailView;
import nucleus.presenter.Presenter;

public class MerchantDetailPresenter extends Presenter<MerchantDetailView> {
    @Inject
    DtlInteractor interactor;

    @Override
    protected void onTakeView(MerchantDetailView merchantsListView) {
        super.onTakeView(merchantsListView);

        interactor.perform();
    }
}
