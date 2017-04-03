package io.github.quotecc.splash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
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
    boolean created; //represents whether the current note is already in the db

    //final String fileName = "splashNotes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_screen);
        created = false;

        title = getIntent().getStringExtra("title");



        stylBar = (Toolbar) findViewById(R.id.toolbar);

        stylBar.setTitle(title); //Limit the num of chars here, to prevent removing bar opts
        setSupportActionBar(stylBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        e = (EditText) findViewById(R.id.noteBody);


        eds = new EntryDataSource(this);
        eds.open();
        Cursor c = eds.queryEntry(title);
        if (c.getCount() >= 1){
            created = true;
            //If the cursor isn't null, means theres already an entry w/ that title
            c.moveToFirst();
            e.setText(c.getString(1));
        }

        //eds.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //eds.open();
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_save:
                String s = stylBar.getTitle()+ " Saved";
                Toast.makeText(NoteScreen.this,s,Toast.LENGTH_LONG).show();
                if (created){ //update
                    Log.d("updte", eds.updateEntry(new Entry(title, e.getText().toString()))+" Entries changed");
                }
                else{ //create
                    Entry en = eds.createEntry(title, e.getText().toString());
                    Log.d("crete", en.toString());
                }



                break;
            case R.id.action_delete:
                AlertDialog.Builder build = new AlertDialog.Builder(NoteScreen.this);
                build.setTitle("Delete?");

                build.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eds.deleteEntry(new Entry(title, e.getText().toString()));
                        Toast.makeText(NoteScreen.this, "Item Deleted from Notes", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                build.show();



                //Toast.makeText(NoteScreen.this, "Delete (Ignore Icon)", Toast.LENGTH_LONG).show();
                break;
            default:
                //eds.close();
                return true;
        }
        //eds.close();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        eds.close();
        super.onPause();
    }
}
