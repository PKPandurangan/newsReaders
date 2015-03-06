package com.feed.arivusitrus.finalfeed;

/**
 * Created by Arivu Sitrus on 02-03-2015.
 */

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RSSReaderActivity extends ListActivity {
    private ArrayList<RSSItem> itemlist = null;
    public RSSListAdaptor rssadaptor = null;
    RSSItem data;
    ListView lv;ArrayList<String> results = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        itemlist = new ArrayList<RSSItem>();
        lv = (ListView) findViewById(R.id.rssChannelListView);
        new RetrieveRSSFeeds().execute();
        if (isConnected()) {
            // tvIsConnected.setBackgroundColor(0xFF00CC00);
            Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG)
                    .show();

        } else {
            Intent i = new Intent(this,Second.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG)
                    .show();
        }


    }
    private boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        data = itemlist.get(position);
        Intent i = new Intent(this,Second.class);
        String s=data.link;
        i.putExtra("myname", s);
        startActivity(i);
       /* DataBaseHub dbh = new DataBaseHub(RSSReaderActivity.this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues
                cv = new ContentValues();
       // cv.put(DataBaseHub.Eid, 1);
        cv.put(DataBaseHub.Ename, s);

        db.insert(DataBaseHub.Emp1, null, cv);
        Toast.makeText(getApplicationContext(), "Hey",
                Toast.LENGTH_LONG).show();

       Intent i = new Intent(this,Second.class);

        i.putExtra("myname", s);
        startActivity(i);
      //
        //*/
    }

    private void retrieveRSSFeed(String urlToRssFeed,ArrayList<RSSItem> list)
    {
        try
        {
            URL url = new URL(urlToRssFeed);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            RSSParser theRssHandler = new RSSParser(list);

            xmlreader.setContentHandler(theRssHandler);

            InputSource is = new InputSource(url.openStream());

            xmlreader.parse(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class RetrieveRSSFeeds extends AsyncTask<Void, Void, Void>
    {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            retrieveRSSFeed("http://www.nasa.gov/rss/dyn/image_of_the_day.rss",itemlist);

            rssadaptor = new RSSListAdaptor(RSSReaderActivity.this, R.layout.rssitemview,itemlist);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(
                    RSSReaderActivity.this, null, "Loading RSS Feeds...");

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(rssadaptor);

            progress.dismiss();

            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public class RSSListAdaptor extends ArrayAdapter<RSSItem>{
        private List<RSSItem> objects = null;

        public RSSListAdaptor(Context context, int textviewid, List<RSSItem> objects) {
            super(context, textviewid, objects);

            this.objects = objects;
        }

        @Override
        public int getCount() {
            return ((null != objects) ? objects.size() : 0);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public RSSItem getItem(int position) {
            return ((null != objects) ? objects.get(position) : null);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if(null == view)
            {
                LayoutInflater vi = (LayoutInflater)RSSReaderActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.rssitemview, null);
            }

           data = objects.get(position);

            if(null != data)
            {
                TextView title = (TextView)view.findViewById(R.id.txtTitle);
                TextView date = (TextView)view.findViewById(R.id.txtDate);
               TextView description = (TextView)view.findViewById(R.id.txtDescription);
String s= String.valueOf(title);
                title.setText(data.title);


                date.setText("on " + data.date);
               description.setText(data.description);
                DataBaseHub dbh=new DataBaseHub(RSSReaderActivity.this);
                SQLiteDatabase db=dbh.getWritableDatabase();
                ContentValues cv=new ContentValues();
                for (int i=0; i<itemlist.size(); i++)
                {
                    //  itemlist.get(i);

                    cv.put(DataBaseHub.Eid, String.valueOf( itemlist.get(i)));
                    Log.i("item", String.valueOf(itemlist.get(i)));
                    cv.put(DataBaseHub.Ename,String.valueOf( itemlist.get(i)));
                    cv.put(DataBaseHub.Eadd,String.valueOf( itemlist.get(i)));

                    db.insert(DataBaseHub.Emp1, null, cv);
                    Toast.makeText(getApplicationContext(),"Hey",Toast.LENGTH_LONG).show();
                }


                SQLiteDatabase db1 = dbh.getReadableDatabase();

                boolean st=true;
                lv.setAdapter(null);
                Cursor cursor = db1.query("Emp1", null, null, null, null, null,
                        null, null);
                if (cursor != null)
                    if (cursor.moveToFirst()) {
                        do {
                            String name1 = cursor.getString(cursor
                                    .getColumnIndex("Ename"));
                            String add = cursor.getString(cursor
                                    .getColumnIndex("Eadd"));
                            Integer id = cursor.getInt(cursor
                                    .getColumnIndex("Eid"));
                            if(st)
                            {
                                results.add("Id:" + id + "\nName:" + name1
                                        + "\nAddress:" + add + "\n");
                            }
                        } while (cursor.moveToNext());
                    }
                lv.setAdapter(new ArrayAdapter<String>(
                        RSSReaderActivity.this, android.R.layout.simple_list_item_1,
                        results));


                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG);
            }
           // database();
           // Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG);
            return view;
           // database();
        }

    }
   /* public void database()
    {

       DataBaseHub dbh=new DataBaseHub(this);
        SQLiteDatabase db=dbh.getWritableDatabase();
        ContentValues cv=new ContentValues();
        for (int i=0; i<itemlist.size(); i++)
        {
          //  itemlist.get(i);
            cv.put(DataBaseHub.Eid, String.valueOf( itemlist.get(i)));
            Log.i("item", String.valueOf(itemlist.get(i)));
            cv.put(DataBaseHub.Ename,String.valueOf( itemlist.get(i)));
            cv.put(DataBaseHub.Eadd,String.valueOf( itemlist.get(i)));
            db.insert(DataBaseHub.Emp1, null, cv);
        }

        Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG);
    }
*/
}