package my.app.conductorjavatest.service;

import android.content.Context;
import android.widget.Toast;

public interface Interactor {
    //stub
    void perform();

    class InteractorImpl implements Interactor {
        private Context context;

        public InteractorImpl(Context context) {
            this.context = context;
        }

        @Override
        public void perform() {
            Toast.makeText(context, "Action was performed", Toast.LENGTH_SHORT).show();
        }
    }
}
