package my.app.conductorjavatest.mosby;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MvpConductorDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * Creates the presenter instance
     *
     * @return the created presenter instance
     */
    @NonNull
    public P createPresenter();

    /**
     * Get the presenter. If null is returned, then a internally a new presenter instance gets
     * created
     * by calling {@link #createPresenter()}
     *
     * @return the presenter instance. can be null.
     */
    @Nullable
    public P getPresenter();

    /**
     * Sets the presenter instance
     *
     * @param presenter The presenter instance
     */
    public void setPresenter(@NonNull P presenter);

    /**
     * Get the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    @NonNull public V getMvpView();
}
