package com.mushishi78.androidrecovery;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class AndroidRecoveryService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("AndroidRecovery", "Service.onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("AndroidRecovery", "Service.onDestroy");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("AndroidRecovery", "Service.onTaskRemoved");
        SharedPreferences sharedPref = getSharedPreferences("AndroidRecovery", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        stopSelf();
    }
}
