package my.app.conductorjavatest;

import android.app.Application;

import my.app.conductorjavatest.di.ApplicationComponent;
import my.app.conductorjavatest.di.DaggerApplicationComponent;
import timber.log.Timber;

public class App extends Application {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        applicationComponent = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent applicationComponent() {
        return applicationComponent;
    }
}
