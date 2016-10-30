package my.app.conductorjavatest.view;


import com.hannesdorfmann.mosby.mvp.MvpView;

public interface LandingView extends MvpView {

   void navigateToLocations();

   void navigateToMerchants();

   void navigateToMap();
}
