package my.app.conductorjavatest.di;

import dagger.Component;

@Component
public interface ApplicationComponent {
    ControllerComponent plus();
}
