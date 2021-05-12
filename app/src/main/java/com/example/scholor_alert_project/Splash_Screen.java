package com.example.scholor_alert_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {

    ImageView img1,img2, gotTo_next;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),StartPage.class));

            }
        };
        Timer spl = new Timer();
        spl.schedule(task,5000);

        img1 = findViewById(R.id.image1);
        img2= findViewById(R.id.text_headline);
        gotTo_next = findViewById(R.id.next);
        lottieAnimationView=findViewById(R.id.logo);
      //  img2=findViewById(R.id.statme);

        img1.animate().translationY(1600).setDuration(100).setStartDelay(4000);
        img2.animate().translationY(1400).setDuration(100).setStartDelay(4000);
        gotTo_next.animate().translationY(1600).setDuration(100).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1600).setDuration(100).setStartDelay(4000);

//        gotTo_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  Intent startPage= new Intent(Splash_Screen.this,StartPage.class);
//                //startActivity(startPage);
//                //finish();
//            }
//        });

    }

}