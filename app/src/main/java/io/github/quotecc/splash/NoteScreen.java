package io.github.quotecc.splash;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;

public class NoteScreen extends AppCompatActivity {

    private EntryDataSource eds;

    EditText e;
    Toolbar stylBar;
    String title;
    final String fileName = "splashNotes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_screen);


        title = getIntent().getStringExtra("title");



        stylBar = (Toolbar) findViewById(R.id.toolbar);
        stylBar.setTitle(title); //Limit the num of chars here, to prevent removing bar opts
        setSupportActionBar(stylBar);

        e = (EditText) findViewById(R.id.noteBody);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_save:
                String s = stylBar.getTitle()+", "+e.getText().toString();
                Toast.makeText(NoteScreen.this,s,Toast.LENGTH_LONG).show();

                try{
                    FileOutputStream opnFile = openFileOutput(fileName, Context.MODE_PRIVATE);
                }
                catch (Exception e){
                    Log.d("DEBUG", "Error opening file");
                }

                //Put logic for saving the file here
                break;
            case R.id.action_delete:
                Toast.makeText(NoteScreen.this, "Delete (Ignore Icon)", Toast.LENGTH_LONG).show();
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
