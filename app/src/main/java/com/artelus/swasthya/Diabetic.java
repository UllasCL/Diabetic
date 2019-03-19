package com.artelus.swasthya;

import android.app.Application;
import android.content.Intent;

import com.batch.android.Batch;
import com.batch.android.BatchActivityLifecycleHelper;
import com.batch.android.Config;
import com.artelus.swasthya.R;

public class Diabetic extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        Batch.setConfig(new Config("DEV5B18D6921A6BBBBC6FA4E761E9F"));
        registerActivityLifecycleCallbacks(new BatchActivityLifecycleHelper());

        // You should configure your notification's customization options here.
        // Not setting up a small icon could cause a crash in applications created with Android Studio 3.0 or higher.
        // More info in our "Customizing Notifications" documentation
         Batch.Push.setSmallIconResourceId(R.drawable.ic_action_em);
    }
}