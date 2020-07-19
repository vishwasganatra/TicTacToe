package com.vishwas.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class player extends AppCompatActivity {
private AdView myAdView;
private EditText et1;

    private EditText et2;
private ImageButton ibt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        et2 = findViewById(R.id.editText);
        et1 = findViewById(R.id.editText4);
        ibt1 = findViewById(R.id.imageButton5);

        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476 banner3
        MobileAds.initialize(this, "ca-app-pub-7866343935318771~9805549476");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        myAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);


    }


    public void pass(View view)
    {
        if(et1.getText().toString().equals(et2.getText().toString()))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(player.this);
            builder.setMessage("Both Name can't be equal!!!").setPositiveButton("Ok", null);
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if(et1.getText().toString().isEmpty() && et2.getText().toString().isEmpty())
        {
            et1.setError("Please Enter Name:");
            et2.setError("Please Enter Name:");
        }
        else if(et2.getText().toString().isEmpty())
        {
            et2.setError("Please Enter Name:");
        }
        else if(et1.getText().toString().isEmpty())
        {
            et1.setError("Please Enter Name:");
        }
        else
        {
            Intent intent = new Intent(player.this, MainActivity.class);
            String p1 = et1.getText().toString();
            String p2 = et2.getText().toString();
            intent.putExtra("player1", p1);
            intent.putExtra("player2", p2);
            startActivity(intent);
        }
    }



}
