import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class CustomAsyncTask {
    private final ExecutorService es = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void execute() {
        onPreExecute();

        es.execute(() -> {
            Object o = doInBackground();

            handler.post(() -> onPostExecute(o));
        });
    }

    protected abstract void onPreExecute();

    protected abstract Object doInBackground();

    protected abstract void onPostExecute(Object o);

    public void cancel() {
        es.shutdown();
    }
}
