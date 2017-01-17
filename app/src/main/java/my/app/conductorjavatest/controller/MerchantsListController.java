package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import my.app.conductorjavatest.App;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.di.ControllerComponent;
import my.app.conductorjavatest.presenter.MerchantsListPresenter;
import my.app.conductorjavatest.view.MerchantsListView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MerchantsListPresenter.class)
@Layout(R.layout.merchants_list)
public class MerchantsListController extends InjectablePresenterController<MerchantsListPresenter, ControllerComponent>
        implements MerchantsListView {

    @BindView(R.id.stateCounterTextView)
    AppCompatTextView stateCounterTextView;

    @State
    int stateCounter = 0;

    @OnClick(R.id.openDetails)
    void openDetailsClicked() {
        ((MerchantsMasterDetailController) getParentController()).navigateToDetails();
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        showCurrentState();
    }

    private void showCurrentState() {
        stateCounterTextView.setText("Current state: " + stateCounter);
    }

    @OnClick(R.id.stateCounterIncrementButton)
    void stateIncrementClicked() {
        stateCounter++;
        showCurrentState();
    }

    @Override
    protected ControllerComponent component() {
        return App.applicationComponent().plus();
    }
}
