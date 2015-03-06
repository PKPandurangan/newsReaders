package com.feed.arivusitrus.finalfeed;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Arivu Sitrus on 02-03-2015.
 */
public class Second extends Activity {
    private WebView mWebview ;Intent intent;
    TextView t4;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
       // mWebview = (WebView) findViewById(R.id.webView1);
       // webView.getSettings().setJavaScriptEnabled(true);
        Intent i = getIntent();
        String s = i.getStringExtra("myname");
        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebview .loadUrl(s);
        setContentView(mWebview );

    }
public void database(RSSItem k)
{

    DataBaseHub dbh=new DataBaseHub(this);
    SQLiteDatabase db=dbh.getWritableDatabase();
    ContentValues cv=new ContentValues();
    cv.put(DataBaseHub.Eid,k.title);
    cv.put(DataBaseHub.Ename,"Test");
    cv.put(DataBaseHub.Eadd,"Ok");
    db.insert(DataBaseHub.Emp1, null, cv);
    Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG);
}
}


