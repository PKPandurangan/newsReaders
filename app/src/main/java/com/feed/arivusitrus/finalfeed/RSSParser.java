package com.feed.arivusitrus.finalfeed;

/**
 * Created by Arivu Sitrus on 02-03-2015.
 */

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class RSSParser extends DefaultHandler {
    private final static String TAG_ITEM = "item";
    private final static String[] xmltags = { "title", "link", "pubDate", "description" };

    RSSItem s;
    private RSSItem currentitem = null;
    private ArrayList<RSSItem> itemarray = null;
    private int currentindex = -1;
    private boolean isParsing = false;
    private StringBuilder builder = new StringBuilder();

    public RSSParser(ArrayList<RSSItem> itemarray) {
        super();

        this.itemarray = itemarray;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if(isParsing && -1 != currentindex && null != builder)
        {
            builder.append(ch,start,length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if(localName.equalsIgnoreCase(TAG_ITEM))
        {
            currentitem = new RSSItem();
            currentindex = -1;
            isParsing = true;

            itemarray.add(currentitem);

     /* for (RSSItem s : itemarray){
                //s.getTitle();
               // RSSDatabaseHandler.addSite(s);

                Log.i("Title content: ", String.valueOf(s.title));
                Log.i("Link: ", String.valueOf(s.link));
                Log.i("Desc ", String.valueOf(s.description));
         // RSSReaderActivity nam = new RSSReaderActivity();
        //  nam.database(itemarray);
                //Log.i("My array list content: ", String.valueOf(s.title));
            }*/
        }
        else
        {
            currentindex = itemIndexFromString(localName);

            builder = null;

            if(-1 != currentindex)
                builder = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if(localName.equalsIgnoreCase(TAG_ITEM))
        {
            isParsing = false;
        }
        else if(currentindex != -1)
        {
            if(isParsing)
            {
                switch(currentindex)
                {
                    case 0: currentitem.title = builder.toString();
                        //Log.i("Title", String.valueOf(currentitem.title));
                   // Second s=new Second(String.valueOf(currentitem.title));

                       //Toast.makeText(this, String.valueOf(currentitem.title), Toast.LENGTH_LONG).show();
                       // Log.d("Item: ",  String.valueOf(currentitem.title));
                       // System.out.println(String.valueOf(currentitem.title));
                     //   nam.get(String.valueOf(currentitem.title));

                        break;
                    case 1: currentitem.link = builder.toString();
                     //  Log.i("Link", String.valueOf(currentitem.link));

                      //  nam.get(String.valueOf(currentitem.link));
                        break;
                    case 2: currentitem.date = builder.toString();
                     //   Log.i("Date", String.valueOf(currentitem.date));
                     //   nam.get(String.valueOf(currentitem.date));
                        break;
                    case 3: currentitem.description= builder.toString();
                       // Log.i("Date", String.valueOf(currentitem.description));
                        break;
                      /* Second s1=new Second(); */


                    //s1.database(s);
                }
            }
        }
    }

    private int itemIndexFromString(String tagname){
        int itemindex = -1;

        for(int index= 0; index<xmltags.length; ++index)
        {
            if(tagname.equalsIgnoreCase(xmltags[index]))
            {
                itemindex = index;

                break;
            }
        }

        return itemindex;
    }
}