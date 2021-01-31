package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player extends AppCompatActivity {
    TextView playerPositon, playerDuration;
    SeekBar seekBar;
    ImageView btnPlay, btnPause, btnPrev, btnNext;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;
    DB helper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        helper = new DB(getApplicationContext());
        TextView lyrics = findViewById(R.id.txt_lyrics);
        //Get Value From Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        Song song = helper.getData(Integer.parseInt(id));

        lyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String myMessage = song.getLyric();
                bundle.putString("message", myMessage );

                BottomSheetFull bottomSheetFull = new BottomSheetFull();
                bottomSheetFull.setArguments(bundle);
                bottomSheetFull.show(getSupportFragmentManager(), bottomSheetFull.getTag());

            }
        });

        //Return screen
        ImageView next = (ImageView) findViewById(R.id.back_screen);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }

        });

        TextView title = findViewById(R.id.txt_title);
        title.setText(song.getTitle());
        TextView singer = findViewById(R.id.txt_artist);
        singer.setText(song.getAuthor());
        ImageView img = findViewById(R.id.img_song);

        //Play music
        playerPositon = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);

        mediaPlayer = MediaPlayer.create(this, R.raw.anhkhongthathu);

        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);

            }
        };
        int duration = mediaPlayer.getDuration();
        String sDuration = convertFormat(duration);

        //set time to view
        playerDuration.setText(sDuration);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF, .5f, RotateAnimation.RELATIVE_TO_SELF, .5f);
        rotateAnimation.setDuration(10000);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide play btn
                btnPlay.setVisibility(View.GONE);
                //show pause btn
                btnPause.setVisibility(View.VISIBLE);

                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                img.startAnimation(rotateAnimation);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide pause btn
                btnPause.setVisibility(View.GONE);
                //show play btn
                btnPlay.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
                img.clearAnimation();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = mediaPlayer.getCurrentPosition();

                int duration = mediaPlayer.getDuration();

                if(mediaPlayer.isPlaying() && duration != currentPosition){
                    currentPosition = currentPosition + 5000;

                    playerPositon.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = mediaPlayer.getCurrentPosition();

                int duration = mediaPlayer.getDuration();

                if(mediaPlayer.isPlaying() && currentPosition > 5000){
                    currentPosition = currentPosition - 5000;

                    playerPositon.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
                playerPositon.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });



    }

    private String convertFormat(int duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}