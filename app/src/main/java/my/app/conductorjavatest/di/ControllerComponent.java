package my.app.conductorjavatest.di;

import dagger.Subcomponent;
import my.app.conductorjavatest.presenter.LandingPresenter;
import my.app.conductorjavatest.presenter.LocationsPresenter;
import my.app.conductorjavatest.presenter.MapPresenter;
import my.app.conductorjavatest.presenter.DetailPresenter;
import my.app.conductorjavatest.presenter.MerchantsListPresenter;
import my.app.conductorjavatest.presenter.MerchantsMasterDetailPresenter;
import my.app.conductorjavatest.presenter.ToolbarPresenter;

@Subcomponent
public interface ControllerComponent {
    void inject(LandingPresenter presenter);

    void inject(LocationsPresenter presenter);

    void inject(MapPresenter presenter);

    void inject(DetailPresenter presenter);

    void inject(MerchantsListPresenter presenter);

    void inject(MerchantsMasterDetailPresenter presenter);

    void inject(ToolbarPresenter presenter);
}