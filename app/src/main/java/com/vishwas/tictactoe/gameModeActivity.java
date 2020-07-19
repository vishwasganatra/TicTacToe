package com.vishwas.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class gameModeActivity extends AppCompatActivity {
    private InterstitialAd interstitialAd;
    public static String EXTRA_MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new  AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

    }


    public void start1pMatch(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        startActivity(intent);
    }

    public void start2pMatch(View view) {
        Intent intent = new Intent(this, player.class);
        startActivity(intent);
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}
