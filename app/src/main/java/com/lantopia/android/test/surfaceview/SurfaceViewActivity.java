package com.lantopia.android.test.surfaceview;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.lantopia.android.test.R;

@SuppressWarnings("MagicNumber")
public class SurfaceViewActivity extends Activity {
    private static final String TAG = "SurfaceViewActivity";

    private final Paint paint = new Paint();


    {
        paint.setColor(Color.RED);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);

        final SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview_surface);

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            private volatile boolean surfaceExists;

            final RectF oval = new RectF(50f, 50f, 100f, 100f);

            private void update(SurfaceHolder holder) {
                final Canvas canvas = holder.lockCanvas();

                try {
                    canvas.drawOval(oval, paint);
                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }

                Log.d(TAG, String.format("Drew circle in thread %s", Thread.currentThread().getName()));
            }

            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                surfaceExists = true;

                update(holder);

                //noinspection ResultOfObjectAllocationIgnored
                new Thread(new Runnable() {
                    @SuppressWarnings("BusyWait")
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(1000L / 5);

                                if (!surfaceExists) break;

                                update(holder);
                            } catch (InterruptedException ignored) {
                                break;
                            }
                        }
                    }
                }).start();
            }

            @Override
            public void surfaceChanged(final SurfaceHolder holder, final int format, final int width,
                                       final int height) {
                update(holder);
            }

            @Override
            public void surfaceDestroyed(final SurfaceHolder holder) {
                surfaceExists = false;
            }
        });
    }
}
