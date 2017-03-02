package io.github.quotecc.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(Splash.this, NoteScreen.class);
        startActivity(i);
        finish();

        /*
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, NoteScreen.class);
                startActivity(i);
                finish();
            }
        };
        Timer op = new Timer();
        op.schedule(task, 3000);*/


    }
}
