package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;

import butterknife.OnClick;
import butterknife.Optional;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MapPresenter;
import my.app.conductorjavatest.view.MapView;

@Layout(R.layout.map)
public class MapController extends BaseController<MapView, MapPresenter> {

   @NonNull
   @Override
   public MapPresenter createPresenter() {
      return new MapPresenter();
   }

   @Optional
   @OnClick(R.id.backToListButton) void backToListClicked() {
      ((LandingController) getParentController()).navigateToMerchants();
   }
}
