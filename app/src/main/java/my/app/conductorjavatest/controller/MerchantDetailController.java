package my.app.conductorjavatest.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.MerchantDetailPresenter;
import my.app.conductorjavatest.view.MerchantDetailView;

@Layout(R.layout.merchant_detail)
public class MerchantDetailController extends BaseController<MerchantDetailView, MerchantDetailPresenter>
      implements MerchantDetailView {

   @BindView(R.id.stateCounterTextView) AppCompatTextView stateCounterTextView;

   @State int stateCounter = 0;

   @NonNull
   @Override
   public MerchantDetailPresenter createPresenter() {
      return new MerchantDetailPresenter();
   }

   @Override
   protected void onViewBound(@NonNull View view) {
      super.onViewBound(view);
      showCurrentState();
   }

   private void showCurrentState() {
      stateCounterTextView.setText("Current state: " + stateCounter);
   }

   @OnClick(R.id.stateCounterIncrementButton) void stateIncrementClicked() {
      stateCounter++;
      showCurrentState();
   }
}
