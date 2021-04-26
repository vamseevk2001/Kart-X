package com.shapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import java.net.URI;

public class splashScreen extends AppCompatActivity {

    VideoView videoSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        videoSplashScreen = findViewById(R.id.videoSplashScreen);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kartx);
        videoSplashScreen.setVideoURI(video);
        videoSplashScreen.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

        videoSplashScreen.start();
    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}