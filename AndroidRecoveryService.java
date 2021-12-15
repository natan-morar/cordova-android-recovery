package com.mushishi78.androidrecovery;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class AndroidRecoveryService extends Service {
    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        Log.i("AndroidRecovery", "Service.onStartCommand");
        SharedPreferences sharedPref = this.getSharedPreferences("AndroidRecovery", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        this.stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
