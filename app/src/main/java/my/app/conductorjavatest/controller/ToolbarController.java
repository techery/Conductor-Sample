package my.app.conductorjavatest.controller;

import butterknife.OnClick;
import butterknife.Optional;
import my.app.conductorjavatest.Layout;
import my.app.conductorjavatest.R;
import my.app.conductorjavatest.presenter.ToolbarPresenter;
import my.app.conductorjavatest.view.ToolbarView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ToolbarPresenter.class)
@Layout(R.layout.toolbar)
public class ToolbarController extends BaseController implements ToolbarView {

    @OnClick(R.id.locationsButton)
    void onLocationsClicked() {
        ((LandingController) getParentController()).navigateToLocations();
    }

    @Optional
    @OnClick(R.id.mapButton)
    void onMapClicked() {
        ((LandingController) getParentController()).navigateToMap();
    }
}
