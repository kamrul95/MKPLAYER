package com.example.mdkamrul.player;

/**
 * Created by mdkamrul on 07-Nov-16.
 */

public class Song {

    public long id;
    private  String title;
    private  String artist;
    private  String duration;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



    public Song (long id,String title,String artist,String duration){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
    public Song (){}

    public  void setTitle (String title){this.title = title;}
    public  long getId (){
        return id;
    }
    public  String getTitle (){
        return  title;
    }
    public  String getArtist (){
        return  artist;
    }

    @Override
    public String toString() {
        return

                title + '\n' +
                        artist + "\n"+
                        duration
                ;
    }
}
