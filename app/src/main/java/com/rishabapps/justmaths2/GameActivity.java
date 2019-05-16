package com.rishabapps.justmaths2;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class GameActivity extends AppCompatActivity {
    //Initialize variables for use in the methods below
    ImageButton btnCorrect;
    ImageButton btnWrong;
    TextView txtViewQuestion;
    TextView txtViewResult;
    TextView txtTime;
    TextView txtScore;
    boolean isResultCorrect;
    private int seconds = 59;
    private boolean stopTimer = false;
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        txtViewResult = findViewById(R.id.txtViewResult);
        txtViewQuestion = findViewById(R.id.txtViewQuestion);
        txtTime = findViewById(R.id.txtTime);
        txtScore = findViewById(R.id.txtScore);
        btnCorrect = findViewById(R.id.btnCorrect);
        btnWrong = findViewById(R.id.btnWrong);
        timer();

        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(true);
            }
        });

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
            }
        });
            generateQuestion();
        }

        public void verifyAnswer(boolean answer) {
            if (answer == isResultCorrect) {
                score += 5;
                txtScore.setText("SCORE: " + score);
            }
            else {
                Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            }
            generateQuestion();
        }

        private void generateQuestion() {
            isResultCorrect = true;
            Random random = new Random();
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            int result = a + b;
            float r_float = random.nextFloat();
            if (r_float > 0.5f) {
                int result_old = result;
                result = random.nextInt(100);
                if (result != result_old) {
                    isResultCorrect = false;
                }
            }
            txtViewQuestion.setText(a + "+" + b);
            txtViewResult.setText("=" + result);
        }

        private void timer() {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    txtTime.setText("TIME :" + seconds);
                    seconds--;
                    if (seconds < 0) {
                        Intent i = new Intent(GameActivity.this,ScoreActivity.class);
                        i.putExtra("score", score);
                        startActivity(i);
                        stopTimer = true;
                    }
                    if (stopTimer == false) {
                        handler.postDelayed(this,1000);
                    }
                }
            });
        }

        @Override
        protected void onPause() {
            super.onPause();
            stopTimer = false;
            finish();
        }
}
