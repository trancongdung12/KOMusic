package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player extends AppCompatActivity {
    TextView playerPositon, playerDuration,singer, title ;
    SeekBar seekBar;
    ImageView btnPlay, btnPause, btnPrev, btnNext, imgSong, btnShuffle, btnRepeat;
    private Thread threadPrev, threadNext;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;
    ImageView heartBorder;
    ImageView heartBold;
    DB helper;
    Context context;
    Song song;
    private static final String TAG = "Player";
    static boolean shuffleBoolean = false, repeatBoolean = false;
    int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        helper = new DB(getApplicationContext());
        btnShuffle = findViewById(R.id.btn_shuffle);
        btnRepeat = findViewById(R.id.btn_repeat);
        imgSong = findViewById(R.id.img_song);
        singer = findViewById(R.id.txt_artist);
        title = findViewById(R.id.txt_title);
        playerPositon = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);

        //Show lyrics
        TextView lyrics = findViewById(R.id.txt_lyrics);
        song = helper.getData(id);
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

        // set collection
        heartBorder = findViewById(R.id.heart_border);
        heartBold = findViewById(R.id.heart_bold);
        //
        if(helper.checkExistCollection(Account.curId, song.getId())){
            heartBorder.setVisibility(View.GONE);
            heartBold.setVisibility(View.VISIBLE);
        }

        heartBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    heartBorder.setVisibility(View.GONE);
                    heartBold.setVisibility(View.VISIBLE);
                    helper.insertCollection(Account.curId, song.getId());
            }
        });
        heartBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    heartBorder.setVisibility(View.VISIBLE);
                    heartBold.setVisibility(View.GONE);
                    helper.deleteCollection(Account.curId, song.getId());
            }
        });

        //Back screen
        ImageView next = (ImageView) findViewById(R.id.back_screen);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
            }

        });

        //Shuffle
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shuffleBoolean){
                    shuffleBoolean = false;
                    btnShuffle.setColorFilter(getResources().getColor(R.color.colorTint), android.graphics.PorterDuff.Mode.MULTIPLY);
                }else{
                    shuffleBoolean = true;
                    btnShuffle.setColorFilter(getResources().getColor(R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY);
                }



            }
        });

        // Repeat
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeatBoolean){
                    repeatBoolean = false;
                    btnRepeat.setColorFilter(getResources().getColor(R.color.colorTint), android.graphics.PorterDuff.Mode.MULTIPLY);
                }else{
                    repeatBoolean = true;
                    btnRepeat.setColorFilter(getResources().getColor(R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY);
                }


            }
        });

        //Play music
        title.setText(song.getTitle());
        singer.setText(song.getAuthor());
        imgSong.setImageResource(song.getImage());
        mediaPlayer = MediaPlayer.create(this, song.getLink());
        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        };
        int duration = mediaPlayer.getDuration();
        String sDuration = getTimeString(duration);

        //set time to view
        playerDuration.setText(sDuration);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF, .5f, RotateAnimation.RELATIVE_TO_SELF, .5f);
        rotateAnimation.setDuration(10000);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                imgSong.startAnimation(rotateAnimation);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
                imgSong.clearAnimation();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
                playerPositon.setText(getTimeString(mediaPlayer.getCurrentPosition()));
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
    @Override
    protected void onPostResume() {
        nextThreadBtn();
        prevThreadBtn();
        super.onPostResume();
    }

    private void nextThreadBtn() {
        threadNext = new Thread(){
            public void run() {
                super.run();
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextBtnClick();
                    }
                });
            }
        };
        threadNext.start();
    }

    private void nextBtnClick() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            if(shuffleBoolean && !repeatBoolean){
                id = getRandom(4);
            }else if(!shuffleBoolean && !repeatBoolean){
                id = (id +1) > 5 ? 1 : id + 1;
            }
            song = helper.getData(id);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getLink());
            title.setText(song.getTitle());
            singer.setText(song.getAuthor());
            imgSong.setImageResource(song.getImage());
            seekBar.setMax(mediaPlayer.getDuration());
            if(helper.checkExistCollection(Account.curId, song.getId())){
                heartBorder.setVisibility(View.GONE);
                heartBold.setVisibility(View.VISIBLE);
            }else{
                heartBorder.setVisibility(View.VISIBLE);
                heartBold.setVisibility(View.GONE);
            }
            Player.this.runOnUiThread(()-> {
                if(mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(runnable, 500);
                }
            });
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
            mediaPlayer.start();

        }else {
            mediaPlayer.stop();
            mediaPlayer.release();
            if(shuffleBoolean && !repeatBoolean){
                id = getRandom(4);
            }else if(!shuffleBoolean && !repeatBoolean){
                id = (id +1) > 5 ? 1 : id + 1;
            }
            song = helper.getData(id);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getLink());
            title.setText(song.getTitle());
            singer.setText(song.getAuthor());
            imgSong.setImageResource(song.getImage());
            seekBar.setMax(mediaPlayer.getDuration());
            if(helper.checkExistCollection(Account.curId, song.getId())){
                heartBorder.setVisibility(View.GONE);
                heartBold.setVisibility(View.VISIBLE);
            }else{
                heartBorder.setVisibility(View.VISIBLE);
                heartBold.setVisibility(View.GONE);
            }
            Player.this.runOnUiThread(()-> {
                if(mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(runnable, 500);
                }
            });
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        }
    }

    private int getRandom(int i) {
        Random random = new Random();
        return random.nextInt(i+1);

    }

    private void prevThreadBtn() {
        threadPrev = new Thread(){
            public void run() {
                super.run();
                btnPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prevBtnClick();
                    }
                });
            }
        };
        threadPrev.start();
    }

    private void prevBtnClick() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            if(shuffleBoolean && !repeatBoolean){
                id = getRandom(4);
            }else if(!shuffleBoolean && !repeatBoolean){
                id = (id -1) <= 0 ? 4 : id - 1;
            }
            song = helper.getData(id);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getLink());
            title.setText(song.getTitle());
            singer.setText(song.getAuthor());
            imgSong.setImageResource(song.getImage());
            seekBar.setMax(mediaPlayer.getDuration());
            Player.this.runOnUiThread(()-> {
                if(mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(runnable, 500);
                }
            });
            if(helper.checkExistCollection(Account.curId, song.getId())){
                heartBorder.setVisibility(View.GONE);
                heartBold.setVisibility(View.VISIBLE);
            }else{
                heartBorder.setVisibility(View.VISIBLE);
                heartBold.setVisibility(View.GONE);
            }
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
            mediaPlayer.start();

        }else {
            mediaPlayer.stop();
            mediaPlayer.release();
            if(shuffleBoolean && !repeatBoolean){
                id = getRandom(4);
            }else if(!shuffleBoolean && !repeatBoolean){
                id = (id -1) <= 0 ? 4 : id - 1;
            }
            song = helper.getData(id);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getLink());
            title.setText(song.getTitle());
            singer.setText(song.getAuthor());
            imgSong.setImageResource(song.getImage());
            seekBar.setMax(mediaPlayer.getDuration());
            if(helper.checkExistCollection(Account.curId, song.getId())){
                heartBorder.setVisibility(View.GONE);
                heartBold.setVisibility(View.VISIBLE);
            }else{
                heartBorder.setVisibility(View.VISIBLE);
                heartBold.setVisibility(View.GONE);
            }
            Player.this.runOnUiThread(()-> {
                if(mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(runnable, 500);
                }
            });
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        }
    }


    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }
}