package com.gurzelai.adivinaelanimal;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.SystemClock;

public class Retardo extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier("sonidointro", "raw", getPackageName()));
        mp.start();
        SystemClock.sleep(2500);
    }
}