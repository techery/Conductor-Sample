package my.app.conductorjavatest.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import io.dwak.freight.annotation.ControllerBuilder;
import my.app.conductorjavatest.App;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.di.ControllerComponent;
import my.app.conductorjavatest.presenter.DetailPresenter;
import my.app.conductorjavatest.view.MerchantDetailView;
import nucleus.factory.RequiresPresenter;
import timber.log.Timber;

@ControllerBuilder(
        value = "Detail",
        scope = "Detail",
        pushChangeHandler = HorizontalChangeHandler.class,
        popChangeHandler = HorizontalChangeHandler.class
)
@RequiresPresenter(DetailPresenter.class)
@Layout(R.layout.merchant_detail)
public class DetailController extends InjectablePresenterController<DetailPresenter, ControllerComponent>
        implements MerchantDetailView {
    private static final String TEST_EXTRA = "text-extra";

    @BindView(R.id.stateCounterTextView)
    AppCompatTextView stateCounterTextView;

    @State
    int stateCounter = 0;

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        showCurrentState();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        stateCounter = savedInstanceState.getInt(TEST_EXTRA, -1);
        Timber.i("State restored: %s", stateCounter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(TEST_EXTRA, stateCounter);
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
