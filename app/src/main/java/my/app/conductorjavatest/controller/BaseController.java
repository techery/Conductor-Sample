package my.app.conductorjavatest.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import my.app.conductorjavatest.Layout;

public abstract class BaseController<V extends MvpView, P extends MvpPresenter<V>> extends MvpController<V, P> {

   private Unbinder unbinder;

   @NonNull
   @Override
   protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
      Layout layout = getLayoutFromAnnotation(this.getClass());
      if (layout == null) {
         throw new IllegalArgumentException("ConfigurableFragment should have Layout annotation");
      }

      View view = inflater.inflate(layout.value(), container, false);
      unbinder = ButterKnife.bind(this, view);
      onViewBound(view);
      return view;
   }

   protected void onViewBound(@NonNull View view) {
   }

   @Override
   protected void onDestroyView(View view) {
      super.onDestroyView(view);
      unbinder.unbind();
      unbinder = null;
   }

   @Override
   protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
      Icepick.restoreInstanceState(this, savedInstanceState);
   }

   @Override
   protected void onSaveInstanceState(@NonNull Bundle outState) {
      super.onSaveInstanceState(outState);
      Icepick.saveInstanceState(this, outState);
   }

   /**
    * Recursively scans class hierarchy searching for {@link Layout} annotation defined.
    *
    * @param clazz class to search for annotation
    * @return defined layout if any or <b>null</b>
    */
   @Nullable
   private Layout getLayoutFromAnnotation(Class clazz) {
      if (clazz == null || clazz.equals(Object.class)) return null;

      Layout layout = (Layout) clazz.getAnnotation(Layout.class);
      if (layout != null) {
         return layout;
      } else {
         return getLayoutFromAnnotation(clazz.getSuperclass());
      }
   }
}
