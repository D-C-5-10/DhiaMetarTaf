package com.example.dhiametartaf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class SplashscreenActivity extends AppCompatActivity {

    ImageView logo;
    ConstraintLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //affichage full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//binding avec layout splasjscreen
        setContentView(R.layout.activity_splashscreen);

        logo = findViewById(R.id.logo);

        Thread timer = new Thread() {
            public void run() {
                try {
                    // sleep(R.integer.SplashActivityTime);
                    sleep(1000);
                } catch (InterruptedException iEx) {
                    iEx.printStackTrace();
                } finally {
                    //rederiction vers mainactivity
                    Intent mainActivity = new Intent(SplashscreenActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                    finish();
                }
            }
        };
        timer.start();
    }
}
