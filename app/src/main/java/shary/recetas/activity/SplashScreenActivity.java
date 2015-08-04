package shary.recetas.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import shary.recetas.R;

/**
 * Created by Shary on 03/08/2015.
 */


public class SplashScreenActivity extends Activity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 30;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // Set portrait orientation
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            // Hide title bar
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.splash_screen);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            // Simulate a long loading process on application startup.
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent mainIntent = new Intent().setClass(
                            SplashScreenActivity.this, Main.class);
                    startActivity(mainIntent);

                    finish();
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}