package com.feed.arivusitrus.finalfeed;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Arivu Sitrus on 06-03-2015.
 */
public class Third extends Activity {
    ListView llv;ArrayList<String> currentList;
    ArrayList<String> arrayList; private ArrayList<RSSItem> itemlist = null;DataBaseHub db;
    ArrayList<String> results = new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listshow);
        llv=(ListView)findViewById(R.id.listView);
        db = new DataBaseHub(this);
        currentList=(ArrayList<String>) getIntent().getSerializableExtra("value");
        llv.setAdapter(new ArrayAdapter<String>(
               Third.this, android.R.layout.simple_list_item_1,
                currentList));

      /*  Bundle bundel = getIntent().getExtras();
        ArrayList<String> studentList = null;
        if(bundel!=null){
            studentList = (ArrayList<String>) bundel.getSerializable("ArrayList");
        }else{
// Something is wrong
            Log.e("Hp", "Something is wrong with bundel");
        }
        if(studentList!=null){
// if every thing is fine this we'll see
// toast showing first student's name
            Toast.makeText(this, studentList.get(0).toString(),
                    Toast.LENGTH_SHORT).show();
        }else{
            Log.e("hoi", "studentList is null");
        }
*/    }

}
