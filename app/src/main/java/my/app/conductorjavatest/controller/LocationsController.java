package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.LocationsPresenter;
import my.app.conductorjavatest.view.LocationsView;

@Layout(R.layout.locations)
public class LocationsController extends BaseController<LocationsView, LocationsPresenter> implements LocationsView {

   @BindView(R.id.stateCounterTextView) AppCompatTextView stateCounterTextView;

   @State int stateCounter = 0;

   @NonNull
   @Override
   public LocationsPresenter createPresenter() {
      return new LocationsPresenter();
   }

   @OnClick(R.id.merchantsButton)
   void onLocationsClicked() {
      ((LandingController) getParentController()).navigateToMerchants();
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
}
