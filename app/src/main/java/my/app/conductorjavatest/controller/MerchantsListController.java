package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;

import butterknife.OnClick;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MerchantsListPresenter;
import my.app.conductorjavatest.view.MerchantsListView;

@Layout(R.layout.merchants_list)
public class MerchantsListController extends BaseController<MerchantsListView, MerchantsListPresenter> {

   @NonNull
   @Override
   public MerchantsListPresenter createPresenter() {
      return new MerchantsListPresenter();
   }

   @OnClick(R.id.openDetails) void openDetailsClicked() {
      ((MerchantsMasterDetailController) getParentController()).navigateToDetails();
   }
}
