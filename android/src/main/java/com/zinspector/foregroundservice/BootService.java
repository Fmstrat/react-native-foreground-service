package com.zinspector.foregroundservice;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

public class BootService extends HeadlessJsTaskService {

    @Nullable
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        Log.d("jit-boot3", "1");
        return new HeadlessJsTaskConfig(
                "boot",
                extras != null ? Arguments.fromBundle(extras) : Arguments.createMap(),
                50000,
                true);
    }
}
