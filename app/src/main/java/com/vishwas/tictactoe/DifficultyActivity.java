package com.vishwas.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import static com.vishwas.tictactoe.gameModeActivity.EXTRA_MESSAGE;

public class DifficultyActivity extends AppCompatActivity {
    private InterstitialAd init1,init2;
    public static String EXTRA_MESSAGE;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476
        init1 = new InterstitialAd(this);
        init1.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        init1.loadAd(new AdRequest.Builder().build());
        init1.setAdListener(new  AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                init1.loadAd(new AdRequest.Builder().build());
            }

        });

        // Sample AdMob app ID: ca-app-pub-7866343935318771~9805549476
        init2 = new InterstitialAd(this);
        init2.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        init2.loadAd(new AdRequest.Builder().build());
        init2.setAdListener(new  AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                init2.loadAd(new AdRequest.Builder().build());
            }

        });
    }


    public void easy(View view) {
        Intent intent;

        intent = new Intent(this, Main2Activity.class);

        String newMsg = message + "easy";
        intent.putExtra(EXTRA_MESSAGE, newMsg);
        startActivity(intent);
        if (init1.isLoaded()) {
            init1.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");

        }
    }
    public void medium(View view) {
        Intent intent;

            intent = new Intent(this, Main2Activity.class);

        String newMsg = message + "medium";
        intent.putExtra(EXTRA_MESSAGE, newMsg);
        startActivity(intent);
    }

    public void hard(View view) {
        Intent intent;

            intent = new Intent(this, Main2Activity.class);

        String newMsg = message + "hard";
        intent.putExtra(EXTRA_MESSAGE, newMsg);
        startActivity(intent);


        if (init2.isLoaded()) {
            init2.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");

        }
    }

}
