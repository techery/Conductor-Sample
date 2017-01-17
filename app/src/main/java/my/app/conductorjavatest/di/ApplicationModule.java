package my.app.conductorjavatest.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import my.app.conductorjavatest.service.DtlInteractor;

@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    public DtlInteractor provideDtlInteractor(Context context) {
        return new DtlInteractor.DtlInteractorImpl(context);
    }
}
