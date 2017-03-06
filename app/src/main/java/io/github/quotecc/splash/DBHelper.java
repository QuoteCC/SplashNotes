package io.github.quotecc.splash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by cCorliss on 3/2/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NOTES = "snotes";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CONTENT = "content";

    public static final String DATABASE_NAME = "snotes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DB_CREATE = "create table "+ TABLE_NOTES + "( "+ COLUMN_NAME_TITLE + " text not null, " + COLUMN_NAME_CONTENT + " text);";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers){
        Log.w(DBHelper.class.getName(), "Upgrading from vers "+ oldVers+ " to vers " + newVers);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

}
