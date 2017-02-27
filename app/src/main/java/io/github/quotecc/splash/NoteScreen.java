package io.github.quotecc.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NoteScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_screen);

        Toolbar stylBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(stylBar);


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
                Toast.makeText(NoteScreen.this,"Save",Toast.LENGTH_LONG).show();
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
