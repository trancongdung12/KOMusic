package com.example.komusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvPlaylist;
    private RecyclerView rcvRecentSong;
    ArrayList<Playlist> arrayList;
    ArrayList<Song> arrayListSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        rcvPlaylist = findViewById(R.id.rycPlaylist);
        int banner[] = {R.drawable.banner1,R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5};
        arrayList = new ArrayList<>();

        //Bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

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

        List<Song> list= new ArrayList<>();

        list.add(new Song(R.drawable.song,"Ngày hạnh phúc"));
        list.add(new Song(R.drawable.song,"Ngày hạnh phúc"));
        list.add(new Song(R.drawable.song,"Ngày hạnh phúc"));
        list.add(new Song(R.drawable.song,"Ngày hạnh phúc"));
        list.add(new Song(R.drawable.song,"Ngày hạnh phúc"));

        return list;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()){
//                        case R.id.nav_home:
//                            Intent myIntent = new Intent(MainActivity.this, IntroActivity.class);
//                            startActivityForResult(myIntent, 0);
//                            break;
//                    }
                    return true;
                }
            };
}