package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CollectionActivity extends Fragment {
    View viewFragment;
    private RecyclerView rcvRecentSong;
    DB helper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.activity_collection, container, false);
        helper = new DB(getActivity());

        rcvRecentSong = viewFragment.findViewById(R.id.rycCollection);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
        CollectionAdapter adapterSong = new CollectionAdapter( getListSong(), getActivity());
        rcvRecentSong.setAdapter(adapterSong);
        return viewFragment;
    }
    private List<Song> getListSong() {
        List<Song> list = helper.getAllCollection(Account.curId);
        return list;
    }
}