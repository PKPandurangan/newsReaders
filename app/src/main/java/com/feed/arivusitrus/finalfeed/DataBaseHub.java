package com.feed.arivusitrus.finalfeed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHub extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyDatabase";

    // Database table name
    private static final String TABLE_LIST = "MyListItem";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ListItem = "listitem";
    public DataBaseHub(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
	public void onCreate(SQLiteDatabase db) {
        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_LIST + "(" + KEY_ID
                + " INTEGER," + KEY_ListItem + " TEXT" + ")";

        db.execSQL(CREATE_LIST_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);

        // Create tables again
        onCreate(db);
	}

    void addListItem(ArrayList<String> listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < listItem.size(); i++) {

            Log.e("vlaue inserting==", "" + listItem.get(i));
            values.put(KEY_ListItem, listItem.get(i));
            db.insert(TABLE_LIST, null, values);


        }

        db.close(); // Closing database connection
    }

    Cursor getListItem() {
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }
   /* public void UpdateNote(ArrayList<String> listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues editCon = new ContentValues();
        for (int i = 0; i < listItem.size(); i++) {

            Log.e("vlaue updating==", "" + listItem.get(i));
           editCon.put(KEY_ListItem, listItem.get(i));
            db.update(TABLE_LIST, editCon, KEY_ListItem  + "=" +listItem.get(i), null);


        }

      db.close();
    }
    public boolean isSiteExists(String rss_link) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_LIST
                + " WHERE rss_link = '" + rss_link + "'", new String[] {});
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }
*/
}
