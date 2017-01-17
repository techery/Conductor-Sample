package my.app.conductorjavatest.controller;

import my.app.conductorjavatest.DaggerPresenterFactory;
import nucleus.factory.PresenterFactory;
import nucleus.presenter.Presenter;

public abstract class InjectablePresenterController<P extends Presenter> extends BaseController<P> {
    @Override
    protected PresenterFactory<P> presenterFactory() {
        return new DaggerPresenterFactory<>(super.presenterFactory(), component());
    }

    protected abstract <Component> Component component();
}