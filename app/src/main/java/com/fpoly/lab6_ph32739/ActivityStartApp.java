package com.fpoly.lab6_ph32739;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.lab5_ph32739.R;

public class ActivityStartApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anima);
        ImageView view = findViewById(R.id.fpt);
        TextView view1 = findViewById(R.id.txt_welcome);
        view.setAnimation(animation);
        view1.setAnimation(animation);

        nextATV();
    }

    private void nextATV(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        },2800);
    }
}