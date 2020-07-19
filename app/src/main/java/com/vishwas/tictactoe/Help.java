package com.vishwas.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Help extends AppCompatActivity {
    private AdView AdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476 banner3
        MobileAds.initialize(this, "ca-app-pub-7866343935318771~9805549476");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("a-app-pub-3940256099942544/6300978111");
        AdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);
    }
}
