package com.lantopia.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.lantopia.android.test.mediaplayer.MediaPlayerActivity;
import com.lantopia.android.test.surfaceview.SurfaceViewActivity;


@SuppressWarnings("override")
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.surfaceview_btn).setOnClickListener(new ActivityClickListener(SurfaceViewActivity.class));
        findViewById(R.id.mediaplayer_btn).setOnClickListener(new ActivityClickListener(MediaPlayerActivity.class));
    }

    private class ActivityClickListener implements View.OnClickListener {
        private final Class<? extends Activity> activity;

        ActivityClickListener(Class<? extends Activity> activity) { this.activity = activity; }

        public void onClick(final View v) { startActivity(activity); }
    }

    private void startActivity(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
