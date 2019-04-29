package io.github.wojcieh26.thestopwatch;

import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopWatchAct extends AppCompatActivity {

    Button btnstart, btnstop, btnreset;
    ImageView arrow;
    Animation roundingalone;
    Chronometer timerHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        btnreset = findViewById(R.id.btnreset);
        arrow = findViewById(R.id.arrow);
        timerHere = findViewById(R.id.timerHere);

        //create optional animation
        btnstop.setAlpha(0);
        btnreset.setAlpha(0);

        //load animations
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");

        //customize font
        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);
        btnreset.setTypeface(MMedium);

        btnstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //passing animation
                arrow.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();

                //start time
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //stop time and animation
                timerHere.stop();
                arrow.clearAnimation();

                //create optional animation(2)
                btnstop.setAlpha(0);

                btnreset.setAlpha(1);
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset time
                timerHere.setBase(SystemClock.elapsedRealtime());

                //animation
                btnreset.setAlpha(0);
                btnstart.setAlpha(1);

            }
        });

    }
}
