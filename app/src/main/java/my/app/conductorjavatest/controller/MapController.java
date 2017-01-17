package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import icepick.State;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MapPresenter;
import my.app.conductorjavatest.view.MapView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MapPresenter.class)
@Layout(R.layout.map)
public class MapController extends BaseController implements MapView {

   @BindView(R.id.stateCounterTextView) AppCompatTextView stateCounterTextView;

   @State int stateCounter = 0;

   @Override
   protected void onViewBound(@NonNull View view) {
      super.onViewBound(view);
      showCurrentState();
   }

   private void showCurrentState() {
      stateCounterTextView.setText("Current state: " + stateCounter);
   }

   @Optional
   @OnClick(R.id.backToListButton) void backToListClicked() {
      ((LandingController) getParentController()).navigateToMerchants();
   }

   @OnClick(R.id.stateCounterIncrementButton) void stateIncrementClicked() {
      stateCounter++;
      showCurrentState();
   }
}
