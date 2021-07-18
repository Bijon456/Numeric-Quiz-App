package com.example.numericalquiz;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Hard extends AppCompatActivity {
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score=0,numberOfQuestions=0;
    TextView scoreTextView,sumTextView,timerTextView;
    Button button0,button1,button2,button3,playAgain;
    MediaPlayer mp;
    AudioManager audioManager;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    public void chooseAnswer(View view)
    {
        view.startAnimation(buttonClick);
        mp.start();

        if( Integer.toString(locationOfCorrectAnswer).equals( view.getTag().toString()))
        {
            final Toast toast=Toast.makeText(getApplicationContext(),
                    "Correct!",
                    Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
            score++;
        }
        else
        {
            final Toast toast=Toast.makeText(getApplicationContext(),
                    "Wrong!",
                    Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void newQuestion()
    {
        Random rand= new Random();
        final int a = new Random().nextInt(900) + 100;
        final int b = new Random().nextInt(900) + 100;


        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer) {
                answers.add(a + b);
            }
            else
            {
                int wrongAnswer= new Random().nextInt(1800) + 200;
                while(wrongAnswer==a+b);
                {
                    wrongAnswer= new Random().nextInt(1800) + 200;
                }
                answers.add(wrongAnswer);

            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void playAgain(View view)
    {
        view.startAnimation(buttonClick);
        score=0;
        numberOfQuestions=0;
        newQuestion();
        timerTextView.setText("60s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        sumTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(60100,1000)
        {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf( (l/1000)+"s"));
            }
            @Override
            public void onFinish() {
                sumTextView.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        sumTextView=findViewById(R.id.sumtextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        scoreTextView=findViewById(R.id.scoretextView);
        timerTextView=findViewById(R.id.timertextView);
        playAgain=findViewById(R.id.button4);
        playAgain(findViewById(R.id.timertextView));
        mp = MediaPlayer.create(this, R.raw.s1);
    }
}
