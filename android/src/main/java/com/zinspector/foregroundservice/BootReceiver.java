package com.zinspector.foregroundservice;
// package com.nowsci.jitsecurity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;

// import com.nowsci.jitsecurity.MainActivity;
import com.facebook.react.HeadlessJsTaskService;

public class BootReceiver extends BroadcastReceiver {

    private Handler handler = new Handler();

    private Class getMainActivityClass(Context context) {
        String packageName = context.getPackageName();
        Log.d("jit-boot", packageName);
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent == null || launchIntent.getComponent() == null) {
            Log.e("BootHelper", "Failed to get launch intent or component");
            return null;
        }
        try {
            Log.d("jit-boot2", launchIntent.getComponent().getClassName());
            return Class.forName(launchIntent.getComponent().getClassName());
        } catch (ClassNotFoundException e) {
            Log.e("BootHelper", "Failed to get main activity class");
            return null;
        }
    }

    // @Override
    // public String getName() {
    //     return "BootReceiver";
    // }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("jit-boot", "BootHelper Booted!!!!!!!!!!!!!!!");
        Class mainActivityClass = getMainActivityClass(context);
        if (mainActivityClass != null) {
            // Context context = getApplicationContext();
            Intent bootIntent = new Intent(context, BootService.class);
            context.startForegroundService(bootIntent);
            HeadlessJsTaskService.acquireWakeLockNow(context);
            // handler.postDelayed(this, 30000000); // 500 Min

            // Intent it = new Intent(context, mainActivityClass);
            // it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // context.startActivity(it);
        }
        // Intent it = new Intent(context, MainActivity.class);
        // it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // context.startActivity(it);
        Log.d("jit-boot", "2");
    }
}
