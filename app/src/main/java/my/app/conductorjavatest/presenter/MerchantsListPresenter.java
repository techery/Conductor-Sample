package my.app.conductorjavatest.presenter;

import javax.inject.Inject;

import my.app.conductorjavatest.service.DtlInteractor;
import my.app.conductorjavatest.view.MerchantsListView;
import nucleus.presenter.Presenter;

public class MerchantsListPresenter extends Presenter<MerchantsListView> {
    @Inject
    DtlInteractor interactor;

    @Override
    protected void onTakeView(MerchantsListView merchantsListView) {
        super.onTakeView(merchantsListView);

        interactor.perform();
    }
}
