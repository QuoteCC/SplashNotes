package io.github.quotecc.splash;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button u, d;
    MediaPlayer mpU, mpD;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setTitle("Library");
        //THIS IS EXTRANEOUS CODE TO FULFIL AN ASSIGNMENT
        getSupportActionBar().setTitle("Music");



    }

}
