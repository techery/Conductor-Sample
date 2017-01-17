package my.app.conductorjavatest.service;

import android.content.Context;
import android.widget.Toast;

public interface DtlInteractor {
    //stub
    void perform();

    class DtlInteractorImpl implements DtlInteractor {
        private Context context;

        public DtlInteractorImpl(Context context) {
            this.context = context;
        }

        @Override
        public void perform() {
            Toast.makeText(context, "Action was performed", Toast.LENGTH_SHORT).show();
        }
    }
}
