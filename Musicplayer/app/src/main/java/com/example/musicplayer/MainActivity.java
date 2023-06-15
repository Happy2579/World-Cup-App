package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //Widgets
    Button btn_play,btn_pause,btn_back,btn_forward;
    TextView appname,time,songname;
    ImageView imageview;
    SeekBar seekbar;

    //MediaPlayer

    MediaPlayer mediaplayer;

    //Handlers
    Handler handler=new Handler();

    //Variables
    double startTime=0;
    double finalTime=0;
    int forwardTime=10000;
    int backwardTime=10000;
    static int oneTimeOnly=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play=findViewById(R.id.playbtn);
        btn_pause=findViewById(R.id.pausebtn);
        btn_back=findViewById(R.id.backwardbutton);
        btn_forward=findViewById(R.id.forwardbtn);
        appname=findViewById(R.id.appname);
        time=findViewById(R.id.time);
        songname=findViewById(R.id.songname);
        imageview=findViewById(R.id.imageView);
        seekbar=findViewById(R.id.seekbar);

        //mediaplayer

        mediaplayer=MediaPlayer.create(this,R.raw.relaxing);
        seekbar.setClickable(false);

        songname.setText(getResources().getIdentifier(
                "relaxing",
                "raw",
                getPackageName()
        ));

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.pause();
            }
        });

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=(int) startTime;
                if((temp+forwardTime)<=finalTime){
                    startTime=startTime+forwardTime;
                    mediaplayer.seekTo((int) startTime);
                }else{
                    Toast.makeText(MainActivity.this,"Can't Jump Forward!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=(int) startTime;

                if((temp-backwardTime)>0){
                    startTime=startTime-backwardTime;
                    mediaplayer.seekTo((int) startTime);
                }else{
                    Toast.makeText(MainActivity.this,"Can't Go Back!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void playMusic(){
        mediaplayer.start();

        finalTime=mediaplayer.getDuration();
        startTime=mediaplayer.getCurrentPosition();

        if(oneTimeOnly==0){
            seekbar.setMax((int)finalTime);
            oneTimeOnly=1;
        }

        time.setText(String.format(
                "%d min. %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime),
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))
        ));

        seekbar.setProgress((int) startTime);
        handler.postDelayed(UpdateSongTime,100);
    }

    private Runnable UpdateSongTime=new Runnable() {

        @Override
        public void run() {
            startTime=mediaplayer.getCurrentPosition();
            time.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)-
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

            seekbar.setProgress((int)startTime);
            handler.postDelayed(this,100);

        }
    };
}