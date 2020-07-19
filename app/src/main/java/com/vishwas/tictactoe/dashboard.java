package com.vishwas.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class dashboard extends AppCompatActivity {
    Button b1,b2,b3,b4;
    ImageView im;
    TextView txt;
    Animation btt,bttwo,el_bounce;
    private InterstitialAd mInterstitialAd1,helpbtnads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        b1= findViewById(R.id.button2);
        b2=findViewById(R.id.button6);
        b3=findViewById(R.id.button5);
        b4=findViewById(R.id.button7);
        im= findViewById(R.id.imageView);
        txt= findViewById(R.id.textView8);


        btt = AnimationUtils.loadAnimation(this,R.anim.btt);
        bttwo = AnimationUtils.loadAnimation(this,R.anim.bttwo);
        el_bounce = AnimationUtils.loadAnimation(this,R.anim.el_bounce);


        b1.startAnimation(btt);
        b2.startAnimation(btt);
        b3.startAnimation(btt);
        b4.startAnimation(btt);
        im.startAnimation(el_bounce);
        txt.startAnimation(bttwo);


        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476 aboutapp
        mInterstitialAd1 = new InterstitialAd(this);
        mInterstitialAd1.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());
        mInterstitialAd1.setAdListener(new  AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd1.loadAd(new AdRequest.Builder().build());
            }

        });
        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476 helpbtn
        helpbtnads = new InterstitialAd(this);
        helpbtnads.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        helpbtnads.loadAd(new AdRequest.Builder().build());
        helpbtnads.setAdListener(new  AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                helpbtnads.loadAd(new AdRequest.Builder().build());
            }

        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,gameModeActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,Help.class));
                if (helpbtnads.isLoaded()) {
                    helpbtnads.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),about.class));
                if (mInterstitialAd1.isLoaded()) {
                    mInterstitialAd1.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(dashboard.this);
        builder.setMessage("Do you really want to Exit?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dashboard.super.onBackPressed();
            }
        }).setNegativeButton("Cancel",null).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void exitapp(View view) {
        finish();
        System.exit(0);
    }

}
