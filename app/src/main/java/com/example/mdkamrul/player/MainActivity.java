package com.example.mdkamrul.player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import static android.R.attr.duration;
import static android.R.attr.name;
import static com.example.mdkamrul.player.R.layout.customlayout;



public class MainActivity extends AppCompatActivity {
    ListView listViewSong;
    private ArrayList<Song> songArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewSong = (ListView) findViewById(R.id.listViewSong);
        songArrayList = new ArrayList<Song>();

        getSong();

        //Toast.makeText(getApplicationContext(),songArrayList.get(2).getTitle().toString(),Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"Smile",Toast.LENGTH_LONG).show();
        ArrayAdapter<Song> songArrayAdapter = new ArrayAdapter<Song>(getApplicationContext(),android.R.layout.simple_list_item_1,songArrayList);
        listViewSong.setAdapter(songArrayAdapter);

    }

    public  String getSongTitle (){
        Song song = new Song();
        return  song.getTitle();
    }

    public void getSong (){
        ContentResolver musicResolver = getContentResolver();
        Uri Musicuri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(Musicuri,null,null,null,null);
        if (musicCursor!=null && musicCursor.moveToNext()){
            int idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
            int titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int duration = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            //problem in Duration
            do {
                long thisid = musicCursor.getLong(idColumn);
                String titleName= musicCursor.getString(titleColumn);
                String artistName = musicCursor.getString(artistColumn);
                String songDuration = musicCursor.getString(duration);
                int songtime = Integer.valueOf(songDuration);
                //int hrs = (songtime / 3600000);
                int mns = (songtime / 60000) % 60000;
                int scs = songtime % 60000 / 1000;

                String songLength = String.format("%02d:%02d",  mns, scs);
                //Double songDuration = musicCursor.getDouble(duration);
                //Double songLength = (Double.valueOf(duration)/ 60000) % 60000;
                songArrayList.add(new Song(thisid,titleName,artistName,songLength));
            }
            while (musicCursor.moveToNext());

        }

    }
}
