package my.app.conductorjavatest.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    ControllerComponent plus();
}
