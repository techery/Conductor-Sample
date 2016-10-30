package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;

import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MerchantDetailPresenter;
import my.app.conductorjavatest.view.MerchantDetailView;

@Layout(R.layout.merchant_detail)
public class MerchantDetailController extends BaseController<MerchantDetailView, MerchantDetailPresenter> {

   @NonNull
   @Override
   public MerchantDetailPresenter createPresenter() {
      return new MerchantDetailPresenter();
   }
}
