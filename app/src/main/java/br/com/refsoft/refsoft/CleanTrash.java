package br.com.refsoft.refsoft;

import android.app.Application;
import android.util.Log;


public class CleanTrash extends Application {
    private static final String TAG = "CarrosApplication";
    // Singleton
    private static CleanTrash instance = null;

    public static CleanTrash getInstance() {
        return instance;
    }


    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "CarrosApplication.onCreate()");
        // Salva a instância para termos acesso como Singleton
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CarrosApplication.onTerminate()");
    }
}