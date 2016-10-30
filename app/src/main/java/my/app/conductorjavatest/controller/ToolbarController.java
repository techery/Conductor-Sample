package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;

import butterknife.OnClick;
import butterknife.Optional;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.ToolbarPresenter;
import my.app.conductorjavatest.view.ToolbarView;

@Layout(R.layout.toolbar)
public class ToolbarController extends BaseController<ToolbarView, ToolbarPresenter> {

   @NonNull
   @Override
   public ToolbarPresenter createPresenter() {
      return new ToolbarPresenter();
   }

   @OnClick(R.id.locationsButton) void onLocationsClicked() {
      ((LandingController) getParentController()).navigateToLocations();
   }

   @Optional
   @OnClick(R.id.mapButton) void onMapClicked() {
      ((LandingController) getParentController()).navigateToLocations();
   }
}
