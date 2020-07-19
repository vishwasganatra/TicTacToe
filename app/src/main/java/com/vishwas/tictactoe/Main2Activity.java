package com.vishwas.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    public static String EXTRA_MESSAGE;
    String message;
    GameState gs;
    int player;
    boolean is2p;
    TextView txtres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gs = new GameState(3);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_3x3);
        txtres = findViewById(R.id.textView16);
        if (savedInstanceState == null) {
            Bundle intent = getIntent().getExtras();
            if(intent == null) {
                message = null;
            } else {
                message = intent.getString(DifficultyActivity.EXTRA_MESSAGE);
            }
        } else {
            message = (String) savedInstanceState.getSerializable(DifficultyActivity.EXTRA_MESSAGE);
        }

        TextView tv = (TextView) findViewById(R.id.p1);
        tv.setText("Player: X ");
        tv = (TextView) findViewById(R.id.p2);
        tv.setText("Computer: O ");
        Random rand;
        rand = new Random();
        if(rand.nextInt()%2 == 0) {
            player = 1;
            gs.computerStart();
            updateButton(new Poi(0,0), -1);
        }
        else {
            player = 1;
        }

    }




    public int getLevel() {
        if(message.equals("easy")) {
            return 0;
        } else if(message.equals("medium")) {
            return 1;
        } else {
            return 2;
        }
    }

    public void updateButton(Poi p, int pl) {
        Button ib;
        int r = p.getX();
        int c = p.getY();

        if(r==0 && c==0) ib = (Button) findViewById(R.id.button_00);
        else if(r==0 && c==1) ib = (Button) findViewById(R.id.button_01);
        else if(r==0 && c==2) ib = (Button) findViewById(R.id.button_02);
        else if(r==1 && c==0) ib = (Button) findViewById(R.id.button_10);
        else if(r==1 && c==1) ib = (Button) findViewById(R.id.button_11);
        else if(r==1 && c==2) ib = (Button) findViewById(R.id.button_12);
        else if(r==2 && c==0) ib = (Button) findViewById(R.id.button_20);
        else if(r==2 && c==1) ib = (Button) findViewById(R.id.button_21);
        else ib = (Button) findViewById(R.id.button_22);

        if(pl == 1) ib.setText("X");
        else ib.setText("O");
    }

    public void setButtonDisable() {
        Button ib;
        ib = (Button) findViewById(R.id.button_00); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_01); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_02); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_10); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_11); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_12); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_20); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_21); ib.setClickable(false);
        ib = (Button) findViewById(R.id.button_22); ib.setClickable(false);

        ib = (Button) findViewById(R.id.button_00); ib.setText("");
        ib = (Button) findViewById(R.id.button_01); ib.setText("");
        ib = (Button) findViewById(R.id.button_02); ib.setText("");
        ib = (Button) findViewById(R.id.button_10); ib.setText("");
        ib = (Button) findViewById(R.id.button_11); ib.setText("");
        ib = (Button) findViewById(R.id.button_12); ib.setText("");
        ib = (Button) findViewById(R.id.button_20); ib.setText("");
        ib = (Button) findViewById(R.id.button_21); ib.setText("");
        ib = (Button) findViewById(R.id.button_22); ib.setText("");
        return;
    }

    public void resetBoard()
    {
        Button ib;
        ib = (Button) findViewById(R.id.button_00); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_01); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_02); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_10); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_11); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_12); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_20); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_21); ib.setClickable(true);
        ib = (Button) findViewById(R.id.button_22); ib.setClickable(true);

return;

    }

    public void input(int r, int c) {
        Poi p = new Poi(r, c);
        Poi q = gs.next(p, player, getLevel());
        /*Log.d("DBG", " p = ( " + p.getX() + ", " + p.getY() + " )");
        Log.d("DBG", " q = ( " + q.getX() + ", " + q.getY() + " )");*/
        if(!(p.getX() == q.getX() && p.getY() == q.getY())) {
            if (p.getX() >= 0 && p.getX() < 3 && p.getY() >= 0 && p.getY() < 3)
                updateButton(p, player);
            if (q.getX() >= 0 && q.getX() < 3 && q.getY() >= 0 && q.getY() < 3)
                updateButton(q, player * (-1));
        }
        if(gs.check1() != 0) { // win
            GameEnd ge = gs.endState();
          Poi[] point = ge.getArr();
            for(int i=0; i<3; i++) {


setButtonDisable();
resetBoard();
                if(ge.getPlayer() == 1) {
                    txtres.setText("You Win!");

                } else {
                    txtres.setText("You Lose!");

                    resetBoard();
                }
            }
            return;
        }
        else if(q.getX() == 3 && q.getY() == 3 || gs.getMoveCount() >= 3*3) { // draw
            setButtonDisable();

            txtres.setText("Match Tie");
            resetBoard();
        }
    }

    public void btn00(View view) {
        input(0, 0);
    }

    public void btn01(View view) {
        input(0, 1);
    }

    public void btn02(View view) {
        input(0, 2);
    }

    public void btn10(View view) {
        input(1, 0);
    }

    public void btn11(View view) {
        input(1, 1);
    }

    public void btn12(View view) {
        input(1, 2);
    }

    public void btn20(View view) {
        input(2, 0);
    }

    public void btn21(View view) {
        input(2, 1);
    }

    public void btn22(View view) {
        input(2, 2);
    }
}

