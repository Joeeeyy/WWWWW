package com.jjoey.walpy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoey.walpy.models.CustomizeFooter;
import com.jjoey.walpy.utils.WalpyPrefs;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        WalpyPrefs walpyPrefs = new WalpyPrefs(this);
        if (walpyPrefs.getCountChecked() > 0){
            Log.d(TAG, "Checked Items:\t" + walpyPrefs.getCountChecked());
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            Log.d(TAG, "Checked Items--NL:\t" + walpyPrefs.getCountChecked());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent splashIntent = new Intent(SplashActivity.this, CustomizeActivity.class);
                    startActivity(splashIntent);
                    finish();
                }
            }, 4000);
        }

    }
}
