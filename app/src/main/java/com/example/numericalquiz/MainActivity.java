package com.example.numericalquiz;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt,bt1,bt2;
MediaPlayer mp;
    AudioManager audioManager;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    public void start(View view)
    {
        view.startAnimation(buttonClick);
        mp.start();

        Intent myIntent = new Intent(getBaseContext(),SecondActivity.class);
        startActivity(myIntent);
    }
    public void start1(View view)
    {
        view.startAnimation(buttonClick);
        mp.start();

        Intent myIntent = new Intent(getBaseContext(),Intermediate.class);
        startActivity(myIntent);
    }
    public void start2(View view)
    {
       view.startAnimation(buttonClick);
         mp.start();
        Intent myIntent = new Intent(getBaseContext(),Hard.class);
        startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        bt=(Button)findViewById(R.id.button);
        bt1=(Button)findViewById(R.id.button5);
        bt2=(Button)findViewById(R.id.button6);
        mp = MediaPlayer.create(this, R.raw.s1);

    }

}
