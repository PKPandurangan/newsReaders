package com.feed.arivusitrus.finalfeed;

import java.util.List;

/**
 * Created by Arivu Sitrus on 02-03-2015.
 */
public class RSSItem {

        public String title;
        public String date;
        public String link;
        public String description;
    List<RSSItem> itemarray;

    // constructor
    public RSSItem(){

    }

    // constructor with parameters
    public RSSItem(String title, String link, String description, String pubdate){
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = pubdate;

    }

    /**
     * All SET methods
     * */
    public void setTitle(String title){
        this.title = title;
    }

    public void setLink(String link){
        this.link = link;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPubdate(String pubDate){
        this.date = pubDate;
    }


    public String getTitle(){
        return this.title;
    }

    public String getLink(){
        return  this.link;
    }

    public String getDescription(){
        return this.description;
    }

    public String getPubdate(){
        return this.date ;
    }


}




