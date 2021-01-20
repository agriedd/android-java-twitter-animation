package com.example.twitteranimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private MotionLayout motionLayout;
    private boolean endTransition = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        } else getActionBar().hide();

        motionLayout = findViewById(R.id.splashContainer);
        motionLayout.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
                if(v == 1.0)
                    endTransition = true;
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if(endTransition)
                    startActivity(
                        new Intent(
                                getApplicationContext(),
                                WelcomeActivity.class
                        )
                    );
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
                endTransition = true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        endTransition = false;
        motionLayout.transitionToStart();
    }
}