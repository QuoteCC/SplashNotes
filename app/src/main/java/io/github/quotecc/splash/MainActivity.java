package io.github.quotecc.splash;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EntryDataSource eds;
    ListView lstVw;
    Button nw;
    String name = "";
    ArrayAdapter<Entry> adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setTitle("Notes");

        //Create the list of all entries
        eds = new EntryDataSource(this);
        eds.open();
        List<Entry> vals = eds.getAllEntries();


        //Add the adapter to the listview to display
        lstVw = (ListView) findViewById(R.id.allNotes);
        adapt = new ArrayAdapter<Entry>(this,android.R.layout.simple_list_item_1, vals);
        lstVw.setAdapter(adapt);
        lstVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        name = "";
        nw = (Button)findViewById(R.id.nw);
        nw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Note Title?");
                final EditText inpt = new EditText(MainActivity.this);
                inpt.setInputType(InputType.TYPE_CLASS_TEXT);
                build.setView(inpt);
            }
        });







    }

}
