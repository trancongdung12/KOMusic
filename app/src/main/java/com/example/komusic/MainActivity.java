package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvPlaylist;
    private RecyclerView rcvRecentSong;
    ArrayList<Playlist> arrayList;
    ArrayList<Song> arrayListSong;
    DB helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        helper = new DB(getApplicationContext());

        helper.insertSong ("Ngày hạnh phúc", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày hạnh", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày phúc", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày hạnh", R.drawable.song, "Ngày hạnh phúc",
                "hanh", "Ngày hạnh phúc huhuhuhu");
        rcvPlaylist = findViewById(R.id.rycPlaylist);
        int banner[] = {R.drawable.banner1,R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5};
        arrayList = new ArrayList<>();

        rcvPlaylist.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        rcvPlaylist.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < banner.length; i++) {
            Playlist itemModel = new Playlist();
            itemModel.setResourceId(banner[i]);

            //add in array list
            arrayList.add(itemModel);
        }

        PlaylistAdapter adapter = new PlaylistAdapter(getApplicationContext(), arrayList);
        rcvPlaylist.setAdapter(adapter);


        //recent song

        rcvRecentSong = findViewById(R.id.rycRecentSong);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
        SongAdapter adapterSong = new SongAdapter(getApplicationContext(), getListSong());
        rcvRecentSong.setAdapter(adapterSong);

        //made for you
        rcvRecentSong = findViewById(R.id.rycMadeForYou);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
        SongAdapter adapterForYou = new SongAdapter(getApplicationContext(), getListSong());
        rcvRecentSong.setAdapter(adapterForYou);

        //singer
        rcvRecentSong = findViewById(R.id.rycSinger);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
        SongAdapter adapterSing = new SongAdapter(getApplicationContext(), getListSong());
        rcvRecentSong.setAdapter(adapterSing);
    }
    private List<Song> getListSong() {
        List<Song> list = helper.getAll();
        Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT).show();
        return list;
    }
    private void render (){
        helper.insertSong ("Ngày hạnh phúc", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày hạnh", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày phúc", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày hạnh", R.drawable.song, "Ngày hạnh phúc",
                "hanh", "Ngày hạnh phúc huhuhuhu");
    }
}