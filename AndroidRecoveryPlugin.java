package com.mushishi78.androidrecovery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidRecoveryPlugin extends CordovaPlugin {
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("save".equals(action)) {
            Log.i("AndroidRecovery", "Plugin.save");

            String key = args.getString(0);
            String value = args.getString(1);

            SharedPreferences sharedPref = this.cordova.getActivity().getSharedPreferences("AndroidRecovery", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(key, value);
            editor.apply();

            callbackContext.success();
            return true;
        }

        if ("recover".equals(action)) {
            Log.i("AndroidRecovery", "Plugin.recover");

            String key = args.getString(0);
            JSONObject result = new JSONObject();

            SharedPreferences sharedPref = this.cordova.getActivity().getSharedPreferences("AndroidRecovery", Context.MODE_PRIVATE);

            if (sharedPref.contains(key)) {
                String value = sharedPref.getString(key, "");
                result.put("value", value);
            }

            callbackContext.success(result);
            return true;
        }

        return false;
    }

    @Override
    public void onDestroy() {
        Log.i("AndroidRecovery", "Plugin.onDestroy");
        Activity context = cordova.getActivity();
        Intent intent = new Intent(context, AndroidRecoveryService.class);
        context.startService(intent);
    }
}
