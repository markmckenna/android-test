package com.lantopia.android.test.mediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import com.lantopia.android.test.R;

public class MediaPlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceview_surface);

        mediaPlayer = MediaPlayer.create(
                this,
                Uri.parse("https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"),
                surfaceView.getHolder());

        mediaPlayer.start();
    }

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
}
