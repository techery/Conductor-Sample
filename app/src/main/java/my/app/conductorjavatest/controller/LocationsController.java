package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;

import butterknife.OnClick;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.LocationsPresenter;
import my.app.conductorjavatest.view.LocationsView;

@Layout(R.layout.locations)
public class LocationsController extends BaseController<LocationsView, LocationsPresenter> {

   @NonNull
   @Override
   public LocationsPresenter createPresenter() {
      return new LocationsPresenter();
   }

   @OnClick(R.id.merchantsButton) void onLocationsClicked() {
      ((LandingController) getParentController()).navigateToMerchants();
   }
}
