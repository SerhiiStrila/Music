package com.home.serega2593.music;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {

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
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        mMediaPlayer = MediaPlayer.create(PlayService.this, R.raw.sound);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.start();
        registerReceiver(mReceiver, intFilt);

        return super.onStartCommand(intent, flags, startId);
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

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Toast.makeText(this, "Play stopping", Toast.LENGTH_LONG).show();
    }
}
