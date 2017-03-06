package io.github.quotecc.splash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cCorliss on 3/2/17.
 */

public class EntryDataSource {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private String[] allColumns = {DBHelper.COLUMN_NAME_TITLE, DBHelper.COLUMN_NAME_CONTENT};

    public EntryDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public Entry createEntry(String title, String content){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME_TITLE, title);
        values.put(DBHelper.COLUMN_NAME_CONTENT, content);
        long insertId = db.insert(DBHelper.TABLE_NOTES, null, values);
        Cursor cursor = db.query(DBHelper.TABLE_NOTES, allColumns, DBHelper.COLUMN_NAME_TITLE + " = " + title, null, null, null, null );
        cursor.moveToFirst();
        Entry e = cursorToEntry(cursor);
        cursor.close();
        return e;
    }

    public void deleteEntry(Entry e){
        String title = e.getTitle();
        db.delete(DBHelper.TABLE_NOTES, DBHelper.COLUMN_NAME_TITLE + " = " + title, null);
    }
    public List<Entry> getAllEntries(){
        List<Entry> ents = new ArrayList<Entry>();
        Cursor cursor = db.query(DBHelper.TABLE_NOTES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Entry e = cursorToEntry(cursor);
            ents.add(e);
            cursor.moveToNext();
        }
        cursor.close();
        return ents;
    }
    private Entry cursorToEntry(Cursor cursor){
        Entry e = new Entry();
        e.setTitle(cursor.getString(0));
        e.setContent(cursor.getString(1));
        return e;
    }
}
