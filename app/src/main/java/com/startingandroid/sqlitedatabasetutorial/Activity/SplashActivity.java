package com.startingandroid.sqlitedatabasetutorial.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.startingandroid.sqlitedatabasetutorial.R;


public class SplashActivity extends AppCompatActivity {

    private int SPLASH_SCREEN_TIMEOUT = 2000;
    private Animation animBounce;
    private TextView txtBounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        //txtBounce=(TextView)findViewById(R.id.bounce);

//        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.bounce);
//        //txtBounce.startAnimation(animBounce);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);


    }
}
