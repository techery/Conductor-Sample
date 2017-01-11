package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import butterknife.BindView;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MerchantsMasterDetailPresenter;
import my.app.conductorjavatest.view.MerchantsMasterDetailView;

@Layout(R.layout.merchants_master_detail)
public class MerchantsMasterDetailController
      extends BaseController<MerchantsMasterDetailView, MerchantsMasterDetailPresenter>
      implements MerchantsMasterDetailView {

   @BindView(R.id.masterContainer) ViewGroup masterContainer;
   @Nullable
   @BindView(R.id.detailContainer) ViewGroup detailContainer;

   @NonNull
   @Override
   public MerchantsMasterDetailPresenter createPresenter() {
      return new MerchantsMasterDetailPresenter();
   }

   @Override
   protected void onViewBound(@NonNull View view) {
      super.onViewBound(view);

      if (isRestoring) return;

      getChildRouter(masterContainer, "merchants_master").setRoot(RouterTransaction.with(new MerchantsListController()));
      if (detailContainer != null) {
         getChildRouter(detailContainer, "merchants_detail").setRoot(RouterTransaction.with(new MapController()));
      }
   }

   @Override
   public void navigateToDetails() {
      if (detailContainer == null) {
         getRouter().pushController(RouterTransaction.with(new MerchantDetailController())
               .pushChangeHandler(new HorizontalChangeHandler())
               .popChangeHandler(new HorizontalChangeHandler()));

      } else {
         getChildRouter(detailContainer, "merchants_detail")
               .pushController(RouterTransaction.with(new MerchantDetailController()));
      }
   }
}
