package com.home.serega2593.music;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service {

    private MediaPlayer mMediaPlayer;

    IntentFilter intFilt = new IntentFilter("BROADCAST_ACTION");
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean status = intent.getBooleanExtra("play", false);
            if (status && mMediaPlayer != null){
                mMediaPlayer.start();
            }
            else {
                if (mMediaPlayer != null)
                    mMediaPlayer.pause();
            }
        }
    };


    @Override
    public void onCreate() {
        mMediaPlayer = MediaPlayer.create(PlayService.this, R.raw.sound);
        mMediaPlayer.start();
        registerReceiver(mReceiver, intFilt);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        if (mMediaPlayer != null) mMediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
