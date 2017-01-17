package my.app.conductorjavatest;

import nucleus.factory.PresenterFactory;
import nucleus.presenter.Presenter;
import timber.log.Timber;

public class DaggerPresenterFactory<P extends Presenter> implements PresenterFactory<P> {
    private Injector injector;

    private PresenterFactory<P> wrappedPresenterFactory;

    public DaggerPresenterFactory(PresenterFactory<P> wrappedPresenterFactory,
                                  Object component) {
        this.wrappedPresenterFactory = wrappedPresenterFactory;
        injector = new Injector(component);
    }

    @Override
    public P createPresenter() {
        P presenter = wrappedPresenterFactory.createPresenter();

        try {
            if (injector.isInjectable(presenter))
                injector.inject(presenter);
            else {
                Timber.i("%s presenter is not injectable", presenter.getClass());
            }
        } catch (Exception exception) {
            Timber.e(exception, "Cannot inject presenter %s", presenter.getClass());
        }

        return presenter;
    }
}
