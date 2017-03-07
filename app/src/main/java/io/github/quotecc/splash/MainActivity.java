package io.github.quotecc.splash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EntryDataSource eds;
    ListView lstVw;
    Toolbar stylBar;
    Button nw;
    String name = "";
    ArrayAdapter<Entry> adapt;
    List<Entry> vals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stylBar = (Toolbar) findViewById(R.id.toolbar2);
        stylBar.setTitle("Notes"); //Limit the num of chars here, to prevent removing bar opts
        setSupportActionBar(stylBar);
        //getActionBar().setTitle("Notes");

        //Create the list of all entries
        eds = new EntryDataSource(this);
        eds.open();
        vals = eds.getAllEntries();


        //Add the adapter to the listview to display
        lstVw = (ListView) findViewById(R.id.allNotes);
        adapt = new ArrayAdapter<Entry>(this,android.R.layout.simple_list_item_1, vals);
        lstVw.setAdapter(adapt);
        lstVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,NoteScreen.class);
                i.putExtra("title", vals.get(position).getTitle());
                startActivity(i);
            }
        });

        name = "";
        /*nw = (Button)findViewById(R.id.nw);
        nw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Note Title?");
                final EditText inpt = new EditText(MainActivity.this);
                inpt.setInputType(InputType.TYPE_CLASS_TEXT);
                build.setView(inpt);
                build.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Pass data to note intent, to start working on note
                    }
                });
                build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        });*/







    }

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
                Log.d("MnuTest", "Save");
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Note Title?");
                final EditText inpt = new EditText(MainActivity.this);
                inpt.setInputType(InputType.TYPE_CLASS_TEXT);
                build.setView(inpt);
                build.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Pass data to note intent, to start working on note
                        String s = inpt.getText().toString();
                        for (Entry e: vals){
                            if (e.getTitle().equals(s)){
                                String err = "There is already a note titled "+ s+", please try a different title, or delete the duplicate";
                                Toast.makeText(MainActivity.this,err,Toast.LENGTH_LONG).show();
                            }
                        }
                        Intent i = new Intent(MainActivity.this,NoteScreen.class);
                        i.putExtra("title", s);
                        startActivity(i);

                    }
                });
                build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                build.show();
                break;
            case R.id.action_delete:

                List<Entry> vals2 = eds.getAllEntries();
                if (!vals.equals(vals2)){
                    vals = vals2;
                    adapt.clear();
                    adapt.addAll(vals);
                    adapt.notifyDataSetChanged();
                }

                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        eds.close();
        super.onPause();
    }
    @Override
    public void onResume(){
        eds.open();
        List<Entry> vals2 = eds.getAllEntries();
        if (!vals.equals(vals2)){
            vals = vals2;
            adapt.clear();
            adapt.addAll(vals);
            adapt.notifyDataSetChanged();
        }
        super.onResume();
    }


}
