package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.LandingPresenter;
import my.app.conductorjavatest.view.LandingView;

@Layout(R.layout.landing)
public class LandingController extends BaseController<LandingView, LandingPresenter> implements LandingView {

   @BindView(R.id.toolbarContainer) ViewGroup toolbarContainer;
   @BindView(R.id.contentContainer) ViewGroup contentContainer;

   @NonNull
   @Override
   public LandingPresenter createPresenter() {
      return new LandingPresenter();
   }

   @Override
   protected void onViewBound(@NonNull View view) {
      super.onViewBound(view);

      if (isRestoring) return;

      getChildRouter(toolbarContainer, "toolbar").setRoot(RouterTransaction.with(new ToolbarController()));
      getChildRouter(contentContainer, "content").setRoot(RouterTransaction.with(new MerchantsMasterDetailController()));
   }

   @Override
   public void navigateToLocations() {
      getChildRouter(contentContainer, "content").pushController(RouterTransaction.with(new LocationsController()));
   }

   @Override
   public void navigateToMerchants() {
      getChildRouter(contentContainer, "content").setRoot(RouterTransaction.with(new MerchantsMasterDetailController()));
   }

   @Override
   public void navigateToMap() {
      getChildRouter(contentContainer, "content").setRoot(RouterTransaction.with(new MapController()));
   }
}
